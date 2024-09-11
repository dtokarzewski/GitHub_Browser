package pl.dtokarzewski.github.core.model.testdata

import pl.dtokarzewski.github.core.model.Commit

fun commitsTestData() = listOf(
    commitTestData(),
)

fun commitTestData() = Commit(
    commit = commitDetailsTestData(),
    sha = "3209d8d1c2b3b22d7a13795176cc7250f6fa5fcc",
    htmlUrl = "https://github.com/dtokarzewski/github-browser/commit/3209d8d1c2b3b22d7a13795176cc7250f6fa5fcc"
)
