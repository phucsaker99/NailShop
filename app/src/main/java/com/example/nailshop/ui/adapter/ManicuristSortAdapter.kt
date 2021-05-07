package com.example.nailshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.R
import com.example.nailshop.data.model.Manicurist

class ManicuristSortAdapter(private val listener: (Manicurist) -> Unit) :
    RecyclerView.Adapter<ManicuristSortViewHolder>() {

    private val manicurists = mutableListOf<Manicurist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManicuristSortViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sort_manicurist_nail, parent, false)
        return ManicuristSortViewHolder(view, listener)
    }

    override fun getItemCount(): Int = manicurists.size

    override fun onBindViewHolder(holder: ManicuristSortViewHolder, position: Int) {
        holder.onBind(manicurists[position])
    }

    fun updateData(newManicurist: List<Manicurist>) {
        manicurists.run {
            clear()
            addAll(newManicurist)
            notifyDataSetChanged()
        }
    }
}