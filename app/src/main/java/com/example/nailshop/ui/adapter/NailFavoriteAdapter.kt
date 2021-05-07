package com.example.nailshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.R
import com.example.nailshop.data.model.Nail

class NailFavoriteAdapter(private val onItemClicked: (Nail) -> Unit) :
    RecyclerView.Adapter<NailFavoriteViewHolder>() {

    private val allMeals = mutableListOf<Nail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NailFavoriteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_nail, parent, false)
        return NailFavoriteViewHolder(view, onItemClicked)
    }

    override fun getItemCount() = allMeals.size

    override fun onBindViewHolder(holder: NailFavoriteViewHolder, position: Int) {
        if (position == 0) {
            holder.onBind(allMeals[position], null)
        } else
            holder.onBind(allMeals[position], allMeals[position - 1])
    }

    fun updateData(newMeals: List<Nail>) {
        allMeals.run {
            clear()
            addAll(newMeals)
            notifyDataSetChanged()
        }
    }
}
