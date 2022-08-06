package lazycoder21.droid.pull_requests.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import lazycoder21.droid.pull_requests.presentation.adapter.base.BaseItemModel

object PullRequestAdapterDiffUtil : DiffUtil.ItemCallback<BaseItemModel>() {
    override fun areItemsTheSame(oldItem: BaseItemModel, newItem: BaseItemModel): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: BaseItemModel, newItem: BaseItemModel): Boolean {
        return false
    }
}