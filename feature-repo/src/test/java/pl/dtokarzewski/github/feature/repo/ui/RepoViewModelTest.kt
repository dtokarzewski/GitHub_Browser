package pl.dtokarzewski.github.feature.repo.ui

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.dtokarzewski.github.core.model.Owner
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.test.MainDispatcherRule
import pl.dtokarzewski.github.data.commit.TestCommitRepository
import pl.dtokarzewski.github.data.repo.TestRepoRepository
import pl.dtokarzewski.github.domain.commit.GetCommitUseCase
import pl.dtokarzewski.github.domain.repo.GetRepoAsFlowUseCse
import pl.dtokarzewski.github.feature.repo.navigation.ARG_OWNER
import pl.dtokarzewski.github.feature.repo.navigation.ARG_REPO_NAME

class RepoViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testRepoRepository = TestRepoRepository()
    private val testCommitRepository = TestCommitRepository()

    private val getRepoAsFlowUseCase = GetRepoAsFlowUseCse(
        repoRepository = testRepoRepository
    )
    private val getCommitUseCase = GetCommitUseCase(
        repoRepository = testRepoRepository,
        commitRepository = testCommitRepository
    )

    private lateinit var viewModel: RepoViewModel

    @Before
    fun setup() {
        val savedStateHandle = SavedStateHandle(mapOf(
            ARG_OWNER to fakeRepo.owner.login,
            ARG_REPO_NAME to fakeRepo.name
        ))
        viewModel = RepoViewModel(
            savedStateHandle = savedStateHandle,
            getRepoAsFlowUseCase = getRepoAsFlowUseCase,
            getCommitUseCase = getCommitUseCase
        )
    }

    @Test
    fun stateIsInitiallyLoading() {
        assertEquals(RepoUiState.Loading, viewModel.uiState.value)
    }

    private val fakeRepo =
        Repo(
            id = 1296269,
            name = "Hello-World",
            fullName = "octocat/Hello-World",
            description =  "This your first repo!",
            owner = Owner(login = "octocat", url = "https://api.github.com/users/octocat"),
            stars = 80
        )
}