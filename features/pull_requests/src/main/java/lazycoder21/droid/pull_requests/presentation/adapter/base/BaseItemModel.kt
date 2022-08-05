package lazycoder21.droid.pull_requests.presentation.adapter.base

import lazycoder21.droid.pull_requests.presentation.adapter.factory.ItemTypeFactory

interface BaseItemModel {
    fun type(typeFactory: ItemTypeFactory): Int
}