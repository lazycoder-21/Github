package lazycoder21.droid.pull_requests.domain.use_case

import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.common.constant.DefaultValues
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.domain.repository.IGithubPullRequestRepository
import lazycoder21.droid.pull_requests.utils.constants.GithubPRStatus
import javax.inject.Inject

class GithubPullRequestUseCase @Inject constructor(
    private val repository: IGithubPullRequestRepository,
) {

    //todo
    suspend fun fetchPullRequests(
        userId: String = DefaultValues.USER_ID,
        repositoryName: String = DefaultValues.REPO_NAME,
        status: GithubPRStatus = GithubPRStatus.Default,
    ): Flow<Resource<List<PullRequest>>> {
        return repository.fetchPullRequests(userId, repositoryName, status)
    }
}