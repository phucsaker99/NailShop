package com.example.nailshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.R
import com.example.nailshop.data.model.Nail

class NailSortAdapter(private val listener: (Nail) -> Unit) :
    RecyclerView.Adapter<NailSortViewHolder>() {

    private val nails = mutableListOf<Nail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NailSortViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sort_manicurist_nail, parent, false)
        return NailSortViewHolder(view, listener)
    }

    override fun getItemCount(): Int = nails.size

    override fun onBindViewHolder(holder: NailSortViewHolder, position: Int) {
        holder.onBind(nails[position])
    }

    fun updateData(newNail: List<Nail>) {
        nails.run {
            clear()
            addAll(newNail)
            notifyDataSetChanged()
        }
    }
}