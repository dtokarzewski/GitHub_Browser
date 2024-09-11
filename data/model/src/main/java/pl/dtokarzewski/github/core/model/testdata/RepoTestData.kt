package pl.dtokarzewski.github.core.model.testdata

import pl.dtokarzewski.github.core.model.Repo

fun repoTestData() = Repo(
    id = 613039715,
    name = "GitHub-Browser",
    fullName = "dtokarzewski/GitHub-Browser",
    description = "Sample project",
    owner = ownerTestData(),
    stars = 0,
)

fun allReposTestData() = listOf(
    repoTestData(),
)
