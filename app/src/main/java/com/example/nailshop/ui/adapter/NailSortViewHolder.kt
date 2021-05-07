package com.example.nailshop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.data.model.Nail
import com.example.nailshop.utils.loadImageCircle
import kotlinx.android.synthetic.main.item_sort_manicurist_nail.view.*

class NailSortViewHolder(
    itemView: View,
    listener: (Nail) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var item: Nail? = null

    init {
        itemView.setOnClickListener {
            item?.let(listener)
        }
    }

    fun onBind(nail: Nail) {
        item = nail
        itemView.apply {
            textTitleSort.text = nail.name
            imageSort.loadImageCircle(nail.image)
            textPrice.text = String.format("Price: %s $", nail.price)
        }
    }
}