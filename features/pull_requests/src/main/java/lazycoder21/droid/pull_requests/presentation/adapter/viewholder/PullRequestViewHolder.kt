package lazycoder21.droid.pull_requests.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import lazycoder21.droid.pull_requests.R
import lazycoder21.droid.pull_requests.databinding.RvPullRequestBinding
import lazycoder21.droid.pull_requests.domain.model.GithubPullRequest
import lazycoder21.droid.pull_requests.presentation.adapter.base.AbstractViewHolder

class PullRequestViewHolder(
    private val binding: RvPullRequestBinding
) : AbstractViewHolder<GithubPullRequest>(binding.root) {

    override fun bind(element: GithubPullRequest) {

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