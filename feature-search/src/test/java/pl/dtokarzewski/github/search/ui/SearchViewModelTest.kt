package pl.dtokarzewski.github.search.ui

import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.dtokarzewski.github.core.test.MainDispatcherRule
import pl.dtokarzewski.github.data.repo.TestRepoRepository
import pl.dtokarzewski.github.domain.repo.GetAllReposUseCase
import pl.dtokarzewski.github.domain.repo.GetRepoUseCase
import pl.dtokarzewski.github.domain.repo.ValidateRepoNameUseCase

class SearchViewModelTest {

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
    fun stateIsInitiallyIdle() {
        val expected = SearchUiState.Idle("dtokarzewski/GitHub_Browser", true, emptyList())
        assertEquals(expected, viewModel.uiState.value)
    }
}