package com.example.nailshop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.data.model.Store
import kotlinx.android.synthetic.main.item_store.view.*

class StoreViewHolder(
    itemView: View,
    itemStoreClicked: (Store) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private var itemStore: Store? = null

    init {
        itemView.setOnClickListener {
            itemStore?.let(itemStoreClicked)
        }
    }

    fun onBind(store: Store) {
        itemStore = store
        itemView.apply {
            textStoreName.text = store.name
            textStoreAddress.text = store.address
        }
    }

}
