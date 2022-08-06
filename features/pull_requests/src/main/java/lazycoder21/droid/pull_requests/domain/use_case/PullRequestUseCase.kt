package lazycoder21.droid.pull_requests.domain.use_case

import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.domain.api_param_model.PullRequestParam
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.domain.repository.IPullRequestRepository
import javax.inject.Inject

class PullRequestUseCase @Inject constructor(
    private val repository: IPullRequestRepository,
) {

    //todo
    suspend fun fetchPullRequests(param: PullRequestParam): Flow<Resource<List<PullRequest>>> {
        return repository.fetchPullRequests(param)
    }
}