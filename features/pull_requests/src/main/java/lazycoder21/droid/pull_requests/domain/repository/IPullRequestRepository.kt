package lazycoder21.droid.pull_requests.domain.repository

import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.domain.api_param_model.PullRequestParam
import lazycoder21.droid.pull_requests.domain.model.PullRequest

interface IPullRequestRepository {

    suspend fun fetchPullRequests(param: PullRequestParam): Flow<Resource<List<PullRequest>>>
}