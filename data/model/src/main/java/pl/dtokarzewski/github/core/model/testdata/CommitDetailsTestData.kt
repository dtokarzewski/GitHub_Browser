package pl.dtokarzewski.github.core.model.testdata

import pl.dtokarzewski.github.core.model.CommitDetails

fun commitDetailsTestData() = CommitDetails(
    author = contributorTestData(),
    commentCount = 0,
    committer = contributorTestData(),
    message = "feat: Dependency update"
)
