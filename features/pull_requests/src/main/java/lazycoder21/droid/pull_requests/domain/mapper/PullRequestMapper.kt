package lazycoder21.droid.pull_requests.domain.mapper

import lazycoder21.droid.pull_requests.data.remote.dto.PullRequestDto
import lazycoder21.droid.pull_requests.domain.model.PullRequest

object PullRequestMapper {

    val List<PullRequestDto>.mapToDomain
        get() = map { it.mapToDomain }

    val PullRequestDto.mapToDomain
        get() = PullRequest(
            title = title,
            closedAt = closedAt,
            createdAt = createdAt,
            user = user.mapToDomain
        )

    val PullRequestDto.User.mapToDomain
        get() = PullRequest.User(avatarUrl, name = login)
}