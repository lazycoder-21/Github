package lazycoder21.droid.pull_requests.data.repository

import lazycoder21.droid.pull_requests.data.remote.dto.GithubPullRequestDto
import lazycoder21.droid.githubclosedpr.utils.constants.GithubPRStatus
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi{

    @GET("$PARAM_USER_ID/$PARAM_REPO_NAME/pulls")
    suspend fun fetchPullRequests(
        @Path(PARAM_USER_ID) userId: String,
        @Path(PARAM_REPO_NAME) repositoryName: String,
        @Query(PARAM_STATUS) status: GithubPRStatus,
    ): Response<List<GithubPullRequestDto>>


    private companion object {
        const val PARAM_USER_ID = "user_id"
        const val PARAM_REPO_NAME = "repo_name"
        const val PARAM_STATUS = "state"
    }
}