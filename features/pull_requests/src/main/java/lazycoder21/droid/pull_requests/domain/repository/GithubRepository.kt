package lazycoder21.droid.pull_requests.domain.repository

import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.pull_requests.domain.model.GithubPullRequest
import lazycoder21.droid.githubclosedpr.utils.constants.GithubPRStatus
import lazycoder21.droid.githubclosedpr.utils.model.Resource

interface GithubRepository {

    suspend fun fetchPullRequests(
        userId: String,
        repositoryName: String,
        status: GithubPRStatus,
    ): Flow<Resource<List<GithubPullRequest>?>>
}