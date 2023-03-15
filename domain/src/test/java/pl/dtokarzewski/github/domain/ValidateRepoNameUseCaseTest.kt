package pl.dtokarzewski.github.domain

import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidateRepoNameUseCaseTest {

    lateinit var useCase: ValidateRepoNameUseCase

    @Before
    fun setup() {
        useCase = ValidateRepoNameUseCase()
    }

    @Test
    fun validateName_onEmpty_Invalid() {
        assertFalse { useCase.invoke("") }
    }

    @Test
    fun validateName_onOwnerOnly_Invalid() {
        assertFalse { useCase.invoke("owner") }
    }

    @Test
    fun validateName_onOwnerWithSlash_Invalid() {
        assertFalse { useCase.invoke("owner/") }
    }

    @Test
    fun validateName_onRepoOnly_Invalid() {
        assertFalse { useCase.invoke("\\repo") }
    }

    @Test
    fun validateName_onDoubleSlash_Invalid() {
        assertFalse { useCase.invoke("owner//repo") }
    }

    @Test
    fun validateName_onMultipleSlash_Invalid() {
        assertFalse { useCase.invoke("owner/repo/repo2") }
    }

    @Test
    fun validateName_onOwnerWithRepo_Valid() {
        assertTrue { useCase.invoke("owner/repo") }
    }

    @Test
    fun validateName_onStandardCharacters_Valid() {
        assertTrue { useCase.invoke("owner_2/repo-3") }
    }
}