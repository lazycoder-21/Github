package lazycoder21.droid.pull_requests.domain.use_case

import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.domain.model.parms.PullRequestParams
import lazycoder21.droid.pull_requests.domain.repository.IGithubPullRequestRepository
import javax.inject.Inject

class GithubPullRequestUseCase @Inject constructor(
    private val repository: IGithubPullRequestRepository,
) {

    //todo
    suspend fun fetchPullRequests(params: PullRequestParams): Flow<Resource<List<PullRequest>>> {
        return repository.fetchPullRequests(params)
    }
}