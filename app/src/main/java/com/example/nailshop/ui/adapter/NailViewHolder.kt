package com.example.nailshop.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.data.model.Nail
import com.example.nailshop.utils.loadImage
import com.example.nailshop.utils.loadImageCircle
import kotlinx.android.synthetic.main.item_nail.view.*

class NailViewHolder(
    itemView: View,
    listenerItem: (Nail) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private var positionNail: Nail? = null

    init {

        itemView.setOnClickListener {
            positionNail?.let(listenerItem)
        }
    }

    @SuppressLint("SetTextI18n")
    fun onBind(nail: Nail) {
        positionNail = nail
        itemView.apply {
            imageNail.loadImageCircle(nail.image)
            textNailName.text = nail.name
            textNailPrice.text = "${nail.price} $"
        }
    }
}
