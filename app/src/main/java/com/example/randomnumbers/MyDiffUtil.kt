package com.example.randomnumbers

import android.os.Bundle

class MyDiffUtil(private val oldList: List<Number>, private val newList: List<Number>) :
    androidx.recyclerview.widget.DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.number == newItem.number
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {

        val bundle = Bundle()

        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        if (oldItem.number != newItem.number)
            bundle.putInt(NEW_NUMBER, newItem.number)

        return bundle
    }

    companion object {
        const val NEW_NUMBER = "RANDOM_KEY"
    }
}