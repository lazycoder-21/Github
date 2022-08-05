package lazycoder21.droid.pull_requests.domain.model

import lazycoder21.droid.pull_requests.presentation.adapter.base.BaseItemModel
import lazycoder21.droid.pull_requests.presentation.adapter.factory.ItemTypeFactory

data class PullRequest(
    val title: String = "",
    val createdAt: String = "",
    val closedAt: String = "",
    val user: User = User(),
) : BaseItemModel {
    data class User(
        val avatarUrl: String = "",
        val id: Int = 0,
        val url: String = "",
    )

    override fun type(typeFactory: ItemTypeFactory): Int {
        return typeFactory.type(this)
    }

}