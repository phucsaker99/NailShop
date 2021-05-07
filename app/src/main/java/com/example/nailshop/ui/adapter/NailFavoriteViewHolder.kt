package com.example.nailshop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.data.model.Nail
import com.example.nailshop.utils.loadImageCircle
import kotlinx.android.synthetic.main.item_favorite_nail.view.*
import java.util.*

class NailFavoriteViewHolder(
    itemView: View,
    onItemClicked: (Nail) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var itemMeal: Nail? = null

    init {
        itemView.setOnClickListener {
            itemMeal?.let(onItemClicked)
        }
    }

    fun onBind(meal: Nail, postMeal: Nail?) {
        itemMeal = meal
        itemView.apply {
            itemMeal?.let {
                if (it.name.substring(1,2) != postMeal?.name?.substring(1,2)) {
                    textSort.visibility = View.VISIBLE
                    textSort.text = meal.name.toUpperCase(Locale.ROOT).substring(1,2)
                }
            }
            imageNail.loadImageCircle(meal.image)
            textTitleNail.text = meal.name
        }
    }
}
