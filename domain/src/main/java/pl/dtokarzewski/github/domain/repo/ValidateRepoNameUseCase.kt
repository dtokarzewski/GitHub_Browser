package pl.dtokarzewski.github.domain.repo

import javax.inject.Inject

class ValidateRepoNameUseCase @Inject constructor() {
    private val repoNamePattern = "[A-Za-z0-9_.-]+/[A-Za-z0-9_.-]+"

    operator fun invoke(repoName: String) = Regex(repoNamePattern)
        .matches(repoName)
}