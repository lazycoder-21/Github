package lazycoder21.droid.pull_requests.domain.mapper

import lazycoder21.droid.pull_requests.data.remote.dto.GithubPullRequestDto
import lazycoder21.droid.pull_requests.domain.model.GithubPullRequest

object GithubItemMappers {

    val List<GithubPullRequestDto>.mapToDomain
        get() = map { it.mapToDomain }

    val GithubPullRequestDto.mapToDomain
        get() = GithubPullRequest(
            title = title,
            closedAt = closedAt,
            createdAt = createdAt,
            user = user.mapToDomain
        )

    val GithubPullRequestDto.User.mapToDomain
        get() = GithubPullRequest.User(avatarUrl, id, url)
}