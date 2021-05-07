package com.example.nailshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.R
import com.example.nailshop.data.model.Store
import java.util.*
import kotlin.collections.ArrayList

class StoreAdapter(private val itemStoreClicked: (Store) -> Unit) :
    RecyclerView.Adapter<StoreViewHolder>(), Filterable {

    private var stores = mutableListOf<Store>()
    private var filterStores = mutableListOf<Store>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view, itemStoreClicked)
    }

    override fun getItemCount() = filterStores.size

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) =
        holder.onBind(filterStores[position])

    override fun getFilter(): Filter = filter

    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Store>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(stores)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                filteredList.addAll(stores.filter {
                    it.name.toLowerCase(Locale.ROOT).contains(filterPattern)
                })
            }
            val result = FilterResults()
            result.values = filteredList
            return result
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filterStores.clear()
            (results?.values as Collection<Store>).let {
                filterStores.addAll(it)
            }
            notifyDataSetChanged()
        }
    }

    fun updateData(newStore: List<Store>) {
        filterStores.apply {
            clear()
            addAll(newStore)
        }
        stores.apply {
            clear()
            addAll(newStore)
        }
        notifyDataSetChanged()
    }
}
