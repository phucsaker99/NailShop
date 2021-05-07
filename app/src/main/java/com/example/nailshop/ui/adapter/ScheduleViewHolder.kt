package com.example.nailshop.ui.adapter

import android.annotation.SuppressLint
import android.text.format.DateFormat
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nailshop.data.model.Bill
import com.example.nailshop.utils.loadImage
import kotlinx.android.synthetic.main.bottom_sheet_detail_nail.view.*
import kotlinx.android.synthetic.main.item_order.view.*
import kotlinx.android.synthetic.main.item_order.view.textPrice

class ScheduleViewHolder(
    itemView: View,
    listener: (Bill) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private var itemBill: Bill? = null

    init {
        itemView.buttonDelete.setOnClickListener {
            itemBill?.let(listener)
        }
    }

    @SuppressLint("SetTextI18n")
    fun onBind(bill: Bill) {
        itemBill = bill
        itemView.apply {
            textPrice.text = "${bill.money} $"
            textDescriptionOrder.text = String.format(
                "Shop: %s\nAddress: %s\nManicurist: %s\nDate: %s",bill.store, bill.address, bill.listManicurist, bill.date
            )
            textNailName.text = bill.listNail
            imageNail.loadImage(bill.imageNail)
        }
    }
}
