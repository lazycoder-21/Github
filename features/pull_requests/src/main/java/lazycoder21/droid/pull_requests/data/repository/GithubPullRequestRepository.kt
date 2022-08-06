package lazycoder21.droid.pull_requests.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.common.utils.errorMessage
import lazycoder21.droid.common.utils.safeApiCall
import lazycoder21.droid.pull_requests.data.remote.GithubPullRequestApi
import lazycoder21.droid.pull_requests.data.remote.dto.GithubPullRequestDto
import lazycoder21.droid.pull_requests.domain.mapper.GithubPullRequestMappers.mapToDomain
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.domain.model.parms.PullRequestParams
import lazycoder21.droid.pull_requests.domain.repository.IGithubPullRequestRepository
import retrofit2.Response
import javax.inject.Inject

class GithubPullRequestRepository @Inject constructor(
    private val githubPullRequestApi: GithubPullRequestApi,
) : IGithubPullRequestRepository {

    override suspend fun fetchPullRequests(
        params: PullRequestParams
    ): Flow<Resource<List<PullRequest>>> = flow {

        emit(Resource.Loading(isLoading = true))

        safeApiCall(block = {
            val data: Response<List<GithubPullRequestDto>> = params.run {
                githubPullRequestApi.fetchPullRequests(
                    userId, repoName, status.status, perPage
                )
            }
            val body = data.body()
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