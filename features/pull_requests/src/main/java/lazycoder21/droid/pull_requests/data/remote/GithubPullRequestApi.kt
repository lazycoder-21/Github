package lazycoder21.droid.pull_requests.data.remote

import lazycoder21.droid.pull_requests.data.remote.dto.GithubPullRequestDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubPullRequestApi {

    @GET("repos/{${PARAM_USER_ID}}/{${PARAM_REPO_NAME}}/pulls")
    suspend fun fetchPullRequests(
        @Path(PARAM_USER_ID) userId: String,
        @Path(PARAM_REPO_NAME) repository: String,
        @Query(PARAM_STATUS) status: String,
    ): Response<List<GithubPullRequestDto>>

    private companion object {
        const val PARAM_USER_ID = "user_id"
        const val PARAM_REPO_NAME = "repo_name"
        const val PARAM_STATUS = "state"
    }
}