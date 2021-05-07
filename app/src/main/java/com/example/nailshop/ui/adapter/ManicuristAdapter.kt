package com.example.nailshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.R
import com.example.nailshop.data.model.Manicurist

class ManicuristAdapter(private val listenerClick: (Manicurist) -> Unit) :
    RecyclerView.Adapter<ManicuristViewHolder>() {

    private var manicurists = mutableListOf<Manicurist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManicuristViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_manicurist, parent, false)
        return ManicuristViewHolder(view, listenerClick)
    }

    override fun getItemCount(): Int = manicurists.size

    override fun onBindViewHolder(holder: ManicuristViewHolder, position: Int) {
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
