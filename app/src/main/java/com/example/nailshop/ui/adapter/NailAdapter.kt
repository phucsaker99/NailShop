package com.example.nailshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.R
import com.example.nailshop.data.model.Nail

class NailAdapter(
    private var listenerItem: (Nail) -> Unit
) : RecyclerView.Adapter<NailViewHolder>() {

    private var nails = mutableListOf<Nail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nail, parent, false)
        return NailViewHolder(view, listenerItem)
    }

    override fun getItemCount(): Int = nails.size

    override fun onBindViewHolder(holder: NailViewHolder, position: Int) {
        holder.onBind(nails[position])
    }

    fun updateData(newNails: List<Nail>) {
        nails.run {
            clear()
            addAll(newNails)
            notifyDataSetChanged()
        }
    }
}
