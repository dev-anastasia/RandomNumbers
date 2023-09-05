package com.example.randomnumbers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

data class Number(
    val id: Long,
    val number: Int
)

class MyAdapter(var list: List<Number>) : RecyclerView.Adapter<NumberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_view, parent, false
        )
        return NumberViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.number.text = list[position].number.toString()
    }

    override fun onBindViewHolder(
        holder: NumberViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val bundle = payloads.first() as Bundle
            for (key in bundle.keySet()) {
                holder.updateNumber(bundle.getInt(key))
            }
        }
    }

}

class NumberViewHolder(itemView: View) : ViewHolder(itemView) {
    val number: TextView = itemView.findViewById(R.id.number)

    fun updateNumber(key: Int) {
        number.text = key.toString()
    }
}