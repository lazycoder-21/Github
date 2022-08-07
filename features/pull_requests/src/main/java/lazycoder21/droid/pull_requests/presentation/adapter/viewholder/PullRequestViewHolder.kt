package lazycoder21.droid.pull_requests.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import lazycoder21.droid.common.utils.attributedText
import lazycoder21.droid.common.utils.loadImage
import lazycoder21.droid.pull_requests.R
import lazycoder21.droid.pull_requests.databinding.RvPullRequestBinding
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.presentation.adapter.base.AbstractViewHolder

class PullRequestViewHolder(
    private val binding: RvPullRequestBinding
) : AbstractViewHolder<PullRequest>(binding.root) {

    override fun bind(element: PullRequest) = with(binding) {
        title.attributedText(R.string.title_s, element.title)
        createdAt.attributedText(R.string.created_at_s, element.createdAt.date)
        closedAt.attributedText(R.string.closed_at_s, element.closedAt.date)
        userName.attributedText(R.string.userid_s, element.user.name)
        userImage.loadImage(element.user.avatarUrl)
    }

    companion object {
        val LAYOUT = R.layout.rv_pull_request

        fun create(
            layoutInflater: LayoutInflater, parent: ViewGroup
        ): PullRequestViewHolder {
            return PullRequestViewHolder(
                RvPullRequestBinding.inflate(layoutInflater, parent, false)
            )
        }
    }
}