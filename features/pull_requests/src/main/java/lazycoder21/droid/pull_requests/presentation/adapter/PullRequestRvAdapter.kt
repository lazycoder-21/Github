package lazycoder21.droid.pull_requests.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        addItems(items)
    }

    fun addItems(items: List<BaseItemModel>) {
        list.addAll(items)
        notifyDataSetChanged()
    }
}