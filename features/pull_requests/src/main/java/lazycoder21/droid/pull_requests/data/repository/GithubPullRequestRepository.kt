package lazycoder21.droid.pull_requests.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.common.utils.errorMessage
import lazycoder21.droid.common.utils.safeApiCall
import lazycoder21.droid.pull_requests.data.remote.GithubPullRequestApi
import lazycoder21.droid.pull_requests.domain.mapper.GithubPullRequestMappers.mapToDomain
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.domain.repository.IGithubPullRequestRepository
import lazycoder21.droid.pull_requests.utils.constants.GithubPRStatus
import javax.inject.Inject

class GithubPullRequestRepository @Inject constructor(
    private val githubPullRequestApi: GithubPullRequestApi,
) : IGithubPullRequestRepository {

    override suspend fun fetchPullRequests(
        userId: String,
        repositoryName: String,
        status: GithubPRStatus,
    ): Flow<Resource<List<PullRequest>>> = flow {

        emit(Resource.Loading(isLoading = true))

        safeApiCall(block = {
            val data = githubPullRequestApi.fetchPullRequests(status.status)
            val body = data.body()
            Log.d("TAG", "fetchPullRequests: $body")
            emit(
                if (data.isSuccessful && body != null) {
                    Resource.Success(body.mapToDomain)
                } else {
                    Resource.Error(data.errorMessage())
                }
            )
        }, error = {
            emit(Resource.Error(it))
        })

        emit(Resource.Loading(isLoading = false))
    }
}