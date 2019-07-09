package com.fglshm.localjsonhandling

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class CountryListAdapter(
    private val ctx: Context
) : RecyclerView.Adapter<CountryListAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val itemList = mutableListOf<MainActivity.Country?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        val countryText = "${item?.name} ----> ${item?.code}"
        holder.itemView.item_text.text = countryText
    }

    fun add(what: MainActivity.Country?) {
        itemList.add(what)
        notifyItemInserted(itemCount)
    }
}