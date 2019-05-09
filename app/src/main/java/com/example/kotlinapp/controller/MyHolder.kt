package com.example.kotlinapp.controller

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.kotlinapp.model.CryptoCurrencies
import kotlinx.android.synthetic.main.currency_layout.view.*


/** Create  a ViewHolder class for the Recyclerview Items*/
class MyHolder(view: View) : RecyclerView.ViewHolder(view) {

    /** Assign values from the data Model to their Corresponding views */

    fun onBind(currency: CryptoCurrencies, listener: MyAdapter.Listener, colors: Array<String>, position: Int) {

        /** listener for user input events */

        itemView.setOnClickListener { listener.onItemClick(currency) }
        itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
        itemView.text_name.text = currency.currency
        itemView.text_price.text = currency.price

    }


}
