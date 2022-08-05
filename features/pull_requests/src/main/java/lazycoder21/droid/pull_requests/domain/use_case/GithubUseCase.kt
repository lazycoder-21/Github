package lazycoder21.droid.pull_requests.domain.use_case

import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.common.constant.DefaultValues
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.domain.model.GithubPullRequest
import lazycoder21.droid.pull_requests.domain.repository.GithubRepository
import lazycoder21.droid.pull_requests.utils.constants.GithubPRStatus

class GithubUseCase(
    private val repository: GithubRepository,
) {

    //todo
    suspend fun fetchPullRequests(
        userId: String = DefaultValues.USER_ID,
        repositoryName: String = DefaultValues.REPO_NAME,
        status: GithubPRStatus = GithubPRStatus.Default,
    ): Flow<Resource<List<GithubPullRequest>>> {
        return repository.fetchPullRequests(userId, repositoryName, status)
    }
}