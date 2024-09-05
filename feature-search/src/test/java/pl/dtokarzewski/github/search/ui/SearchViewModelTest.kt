package pl.dtokarzewski.github.search.ui

import android.content.res.Resources.NotFoundException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.dtokarzewski.github.core.model.Owner
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.test.MainDispatcherRule
import pl.dtokarzewski.github.data.repo.TestRepoRepository
import pl.dtokarzewski.github.domain.repo.GetAllReposUseCase
import pl.dtokarzewski.github.domain.repo.GetRepoUseCase
import pl.dtokarzewski.github.domain.repo.ValidateRepoNameUseCase

class SearchViewModelTest {
    // TODO rename tests to `GIVEN ... WHEN ... THEN ...`
    // Change to assertValueHistoryDiff
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testRepoRepository = TestRepoRepository()
    private val getRepoUseCase = GetRepoUseCase(
        repoRepository = testRepoRepository,
        dispatcher = UnconfinedTestDispatcher()
    )
    private val getAllReposUseCase = GetAllReposUseCase(
        repoRepository = testRepoRepository
    )
    private val validateRepoNameUseCase = ValidateRepoNameUseCase()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        viewModel = SearchViewModel(
            getRepoUseCase = getRepoUseCase,
            validateRepoNameUseCase = validateRepoNameUseCase,
            getAllReposUseCase = getAllReposUseCase
        )
    }

    @Test
    fun stateIsInitiallyIdle() = runTest {
        val expected = SearchUiState.Idle("", false, emptyList())
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun stateInitiallyHasRecentReposWhenAvailable() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(fakeRepos)

        assertEquals(
            SearchUiState.Idle(
                query = "",
                isQueryValid = false,
                allRepos = fakeRepos
            ),
            viewModel.uiState.value
        )
        collectJob.cancel()
    }

    @Test
    fun onQueryChanged_idleStateWithQueryEmitted_whenQuerySet() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(emptyList())

        viewModel.onQueryChanged("octocat/Hello-World")

        assertEquals(
            SearchUiState.Idle(
                "octocat/Hello-World", true, emptyList()
            ),
            viewModel.uiState.value
        )
        collectJob.cancel()
    }

    @Test
    fun onQueryChanged_emptyIdleStateEmitted_whenQueryRemoved() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(emptyList())

        viewModel.onQueryChanged("octocat/Hello-World")
        viewModel.onQueryChanged("")

        assertEquals(
            SearchUiState.Idle("", false, emptyList()),
            viewModel.uiState.value
        )
        collectJob.cancel()
    }

    @Test
    fun onQueryChanged_idleStateWithFailedValidationEmitted_whenInvalidQuery() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        viewModel.onQueryChanged("octocat")
        viewModel.onQueryChanged("")

        assertEquals(false, viewModel.uiState.value.isQueryValid)
        collectJob.cancel()
    }

    @Test
    fun onSearchClicked_loaderShown() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(emptyList())
        viewModel.onQueryChanged("octocat/Hello-World")
        viewModel.onSearchClicked()

        assertEquals(
            SearchUiState.Loading("octocat/Hello-World", true, emptyList()),
            viewModel.uiState.value
        )
        collectJob.cancel()
    }

    @Test
    fun onSearchClicked_navigateToRepoEmitted_whenRepoFound() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(emptyList())

        viewModel.onQueryChanged("octocat/Hello-World")
        viewModel.onSearchClicked()
        testRepoRepository.setRepoResult(Result.success(fakeRepos.first()))

        assertEquals(
            SearchUiState.NavigateToRepo(
                owner = fakeRepos.first().owner.login,
                name = fakeRepos.first().name,
                query = "octocat/Hello-World",
                isQueryValid = true,
                allRepos = emptyList()
            ),
            viewModel.uiState.value
        )
        collectJob.cancel()
    }

    @Test
    fun onSearchClicked_errorShown_whenRepoNotFound() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(emptyList())
        val error = NotFoundException("Repo doesn't exists")

        viewModel.onQueryChanged("octocat/Hello-World")
        viewModel.onSearchClicked()
        testRepoRepository.setRepoResult(Result.failure(error))

        assertEquals(
            SearchUiState.Error(
                error = error,
                query = "octocat/Hello-World",
                isQueryValid = true,
                allRepos = emptyList()
            ),
            viewModel.uiState.value
        )
        collectJob.cancel()
    }

    @Test
    fun onRecentRepoClicked_loaderShown() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(fakeRepos)
        viewModel.onRecentRepoClicked(fakeRepos.first())

        assertEquals(
            SearchUiState.Loading(
                query = "octocat/Hello-World",
                isQueryValid = true,
                allRepos = fakeRepos
            ),
            viewModel.uiState.value
        )
        collectJob.cancel()
    }

    @Test
    fun onRecentRepoClicked_navigateToRepoEmitted_whenRepoFound() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(fakeRepos)

        viewModel.onRecentRepoClicked(fakeRepos.first())
        testRepoRepository.setRepoResult(Result.success(fakeRepos.first()))

        assertEquals(
            SearchUiState.NavigateToRepo(
                owner = fakeRepos.first().owner.login,
                name = fakeRepos.first().name,
                query = "octocat/Hello-World",
                isQueryValid = true,
                allRepos = fakeRepos
            ),
            viewModel.uiState.value
        )
        collectJob.cancel()
    }

    @Test
    fun onRecentRepoClicked_errorShown_whenRepoNotFound() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(fakeRepos)
        val error = NotFoundException("Repo doesn't exists")

        viewModel.onRecentRepoClicked(fakeRepos.first())
        testRepoRepository.setRepoResult(Result.failure(error))

        assertEquals(
            SearchUiState.Error(
                error = error,
                query = "octocat/Hello-World",
                isQueryValid = true,
                allRepos = fakeRepos
            ),
            viewModel.uiState.value
        )
        collectJob.cancel()
    }

    @Test
    fun onNavigated_idleStateEmited() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(fakeRepos)
        viewModel.onQueryChanged("octocat/Hello-World")
        viewModel.onSearchClicked()
        testRepoRepository.setRepoResult(Result.success(fakeRepos.first()))

        viewModel.onNavigated()

        assertEquals(
            SearchUiState.Idle(
                query = "octocat/Hello-World",
                isQueryValid = true,
                allRepos = fakeRepos
            ),
            viewModel.uiState.value
        )
        collectJob.cancel()

    }

    @Test
    fun onErrorShown_idleStateEmited() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        testRepoRepository.setRepos(fakeRepos)
        viewModel.onQueryChanged("octocat/Hello-World")
        viewModel.onSearchClicked()
        testRepoRepository.setRepoResult(Result.failure(NotFoundException("Repo doesn't exists")))

        viewModel.onErrorShown()

        assertEquals(
            SearchUiState.Idle(
                query = "octocat/Hello-World",
                isQueryValid = true,
                allRepos = fakeRepos
            ),
            viewModel.uiState.value
        )
        collectJob.cancel()
    }

    // TODO move to RepoTestData file
    private val fakeRepos = listOf(
        Repo(
            id = 1296269,
            name = "Hello-World",
            fullName = "octocat/Hello-World",
            description = "This your first repo!",
            owner = Owner(login = "octocat", url = "https://api.github.com/users/octocat"),
            stars = 80
        ),
        Repo(
            id = 4234532,
            name = "Hell0-Developer",
            fullName = "octocat/Hello-Developer",
            description = "This your second repo!",
            owner = Owner(login = "octocat", url = "https://api.github.com/users/octocat"),
            stars = 10
        ),
    )
}