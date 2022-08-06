package lazycoder21.droid.pull_requests.domain.model

import lazycoder21.droid.pull_requests.presentation.adapter.base.BaseItemModel
import lazycoder21.droid.pull_requests.presentation.adapter.factory.ItemTypeFactory

object Loading : BaseItemModel {
    override fun type(typeFactory: ItemTypeFactory): Int {
        return typeFactory.type(this)
    }
}