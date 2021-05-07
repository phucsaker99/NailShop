package com.example.nailshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.R
import com.example.nailshop.data.model.Bill

class ScheduleAdapter(private val listener: (Bill) -> Unit) :
    RecyclerView.Adapter<ScheduleViewHolder>() {
    private var schedules = mutableListOf<Bill>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ScheduleViewHolder(view, listener)
    }

    override fun getItemCount() = schedules.size

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.onBind(schedules[position])
    }

    fun updateData(bills: MutableList<Bill>) {
        schedules.run {
            clear()
            addAll(bills)
            notifyDataSetChanged()
        }
    }
}