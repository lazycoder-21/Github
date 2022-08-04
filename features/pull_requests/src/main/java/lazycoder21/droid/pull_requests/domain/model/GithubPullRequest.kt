package lazycoder21.droid.pull_requests.domain.model

data class GithubPullRequest(
    val title: String = "",
    val createdAt: String = "",
    val closedAt: String = "",
    val user: User = User(),
) {
    data class User(
        val avatarUrl: String = "",
        val id: Int = 0,
        val url: String = "",
    )
}