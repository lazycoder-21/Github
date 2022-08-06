package lazycoder21.droid.pull_requests.domain.model.parms

import lazycoder21.droid.common.constant.DefaultValues
import lazycoder21.droid.pull_requests.utils.constants.GithubPRStatus

data class PullRequestParams(
    val userId: String = DefaultValues.USER_ID,
    val repoName: String = DefaultValues.REPO_NAME,
    val status: GithubPRStatus = GithubPRStatus.Default,
    val perPage: Int = DefaultValues.PER_PAGE
)
