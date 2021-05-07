package com.example.nailshop.ui.adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.utils.loadImage
import kotlinx.android.synthetic.main.item_top_manicurist.view.*

class ManicuristViewHolder(
    itemView: View,
    listenerClick: (Manicurist) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private var positionManicurist: Manicurist? = null

    init {
        itemView.setOnClickListener {
            positionManicurist?.let(listenerClick)
        }
    }

    fun onBind(manicurist: Manicurist) {
        positionManicurist = manicurist
        itemView.apply {
            imageManicurist.loadImage(manicurist.image)
            textRank.text = (adapterPosition + 1).toString()
            textNailLike.text = manicurist.like.toString()
            textManicuristName.text = manicurist.name
            textStore.text = manicurist.store
        }
    }
}
