package lazycoder21.droid.pull_requests.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import lazycoder21.droid.pull_requests.R
import lazycoder21.droid.pull_requests.databinding.ProgressBarBinding
import lazycoder21.droid.pull_requests.domain.model.Loading
import lazycoder21.droid.pull_requests.presentation.adapter.base.AbstractViewHolder

class LoadingViewHolder(
    private val binding: ProgressBarBinding
) : AbstractViewHolder<Loading>(binding.root) {

    override fun bind(element: Loading) {

    }

    companion object {
        val LAYOUT = R.layout.progress_bar

        fun create(
            layoutInflater: LayoutInflater, parent: ViewGroup
        ): LoadingViewHolder {
            return LoadingViewHolder(
                ProgressBarBinding.inflate(layoutInflater, parent, false)
            )
        }
    }
}