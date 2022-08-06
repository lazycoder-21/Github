package lazycoder21.droid.pull_requests.domain.api_param_model

import lazycoder21.droid.common.constant.DefaultValues
import lazycoder21.droid.pull_requests.utils.constants.PullRequestStatus

data class PullRequestParam(
    val userId: String = DefaultValues.USER_ID,
    val repoName: String = DefaultValues.REPO_NAME,
    val status: PullRequestStatus = PullRequestStatus.Default,
    val perPage: Int = DefaultValues.PER_PAGE,
    val pageNo: Int = 0
)
