package lazycoder21.droid.githubclosedpr.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import lazycoder21.droid.pull_requests.domain.model.GithubPullRequest
import lazycoder21.droid.pull_requests.domain.repository.GithubRepository
import lazycoder21.droid.githubclosedpr.utils.constants.GithubPRStatus
import lazycoder21.droid.githubclosedpr.utils.model.Resource

class GithubUseCase(
    private val repository: GithubRepository,
) {

    suspend fun fetchPullRequests(
        userId: String,
        repositoryName: String,
        status: GithubPRStatus,
    ): Flow<Resource<List<GithubPullRequest>?>> = flow {
        emit(Resource.Loading(isLoading = true))

        repository.fetchPullRequests(userId, repositoryName, status).onEach {
            emit(it)
        }

        emit(Resource.Loading(isLoading = false))
    }
}