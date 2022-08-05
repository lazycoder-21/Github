package lazycoder21.droid.pull_requests.presentation.adapter.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.presentation.adapter.base.AbstractViewHolder
import lazycoder21.droid.pull_requests.presentation.adapter.viewholder.PullRequestViewHolder

class ItemTypeFactory {

    fun type(pullRequest: PullRequest): Int {
        return PullRequestViewHolder.LAYOUT
    }

    fun createViewHolder(
        layoutInflater: LayoutInflater, parent: ViewGroup, type: Int
    ): AbstractViewHolder<*> {
        return when (type) {
            PullRequestViewHolder.LAYOUT -> PullRequestViewHolder.create(layoutInflater, parent)
            else -> createViewHolder(layoutInflater, parent, type)
        }
    }
}