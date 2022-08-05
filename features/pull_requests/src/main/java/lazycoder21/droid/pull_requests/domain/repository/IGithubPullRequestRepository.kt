package lazycoder21.droid.pull_requests.domain.repository

import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.domain.model.GithubPullRequest
import lazycoder21.droid.pull_requests.utils.constants.GithubPRStatus

interface IGithubPullRequestRepository {

    suspend fun fetchPullRequests(
        userId: String,
        repositoryName: String,
        status: GithubPRStatus,
    ): Flow<Resource<List<GithubPullRequest>>>
}