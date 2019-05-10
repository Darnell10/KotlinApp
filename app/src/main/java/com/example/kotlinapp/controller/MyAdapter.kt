package com.example.kotlinapp.controller

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kotlinapp.R
import com.example.kotlinapp.model.CryptoCurrencies


//Define an array of colors
private val colors: Array<String> = arrayOf(
    "#7E57C2", "#42A5F5", "#26C6DA", "#66BB6A", "#FFEE58", "#FF7043"
    , "#EC407A", "#d32f2f"
)

//Pass the ArrayList and a list, and add a variable to hold your data
class MyAdapter(private val cryptoList: ArrayList<CryptoCurrencies>, private val listener: Listener) :

// Extend Recycler.View

    RecyclerView.Adapter<MyHolder>() {

    interface Listener {
        fun onItemClick(cryptoCurrencies: CryptoCurrencies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_layout, parent, false)
        return MyHolder(view)
    }

    //Bind the ViewHolder
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        //Pass the position where each item should be displayed
        holder.onBind(cryptoList[position], listener, colors, position)

    }

    //Check how many items you have to display
    override fun getItemCount(): Int = cryptoList.count()

}