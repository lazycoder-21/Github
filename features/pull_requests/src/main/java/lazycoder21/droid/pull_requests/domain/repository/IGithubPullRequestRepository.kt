package lazycoder21.droid.pull_requests.domain.repository

import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.domain.model.parms.PullRequestParams

interface IGithubPullRequestRepository {

    suspend fun fetchPullRequests(params: PullRequestParams): Flow<Resource<List<PullRequest>>>
}