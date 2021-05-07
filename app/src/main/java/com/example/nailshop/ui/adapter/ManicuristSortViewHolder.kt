package com.example.nailshop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.utils.loadImageCircle
import kotlinx.android.synthetic.main.item_sort_manicurist_nail.view.*

class ManicuristSortViewHolder(itemView: View, listener: (Manicurist) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private var item: Manicurist? = null

    init {
        itemView.setOnClickListener {
            item?.let(listener)
        }
    }

    fun onBind(manicurist: Manicurist) {
        item = manicurist
        itemView.apply {
            textTitleSort.text = manicurist.name
            imageSort.loadImageCircle(manicurist.image)
        }
    }
}