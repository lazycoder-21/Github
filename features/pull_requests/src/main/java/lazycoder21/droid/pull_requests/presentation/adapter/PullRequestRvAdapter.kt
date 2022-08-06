package lazycoder21.droid.pull_requests.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lazycoder21.droid.pull_requests.domain.model.Loading
import lazycoder21.droid.pull_requests.presentation.adapter.base.AbstractViewHolder
import lazycoder21.droid.pull_requests.presentation.adapter.base.BaseItemModel
import lazycoder21.droid.pull_requests.presentation.adapter.factory.ItemTypeFactory

class PullRequestRvAdapter(
    private val adapterTypeFactory: ItemTypeFactory,
    private val list: MutableList<BaseItemModel> = mutableListOf(),
) : RecyclerView.Adapter<AbstractViewHolder<BaseItemModel>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AbstractViewHolder<BaseItemModel> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return adapterTypeFactory.createViewHolder(
            layoutInflater, parent, viewType
        ) as AbstractViewHolder<BaseItemModel>
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AbstractViewHolder<BaseItemModel>, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type(adapterTypeFactory)
    }

    fun clearAndInsertItems(items: List<BaseItemModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<BaseItemModel>) {
        val loaderIndex = list.lastIndex
        if (list.getOrNull(loaderIndex) is Loading) {
            list.removeLast()
            notifyItemRemoved(loaderIndex)
        }

        val prevSize = list.size
        list.addAll(items)
        notifyItemRangeInserted(prevSize, items.size)
    }

    fun addLoadingState() {
        val loaderIndex = list.lastIndex
        list.add(Loading)
        notifyItemInserted(loaderIndex)
    }
}