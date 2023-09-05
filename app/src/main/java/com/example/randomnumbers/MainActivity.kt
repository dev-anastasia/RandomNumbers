package com.example.randomnumbers

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var myAdapter: MyAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var myList = singletonList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RV + adapter
        val recyclerView: RecyclerView = findViewById(R.id.list)
        myAdapter = MyAdapter(myList)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        // РОбновление списка
        handler.postDelayed({
            singletonList = initList()
            val diff = DiffUtil.calculateDiff(MyDiffUtil(singletonList, myList))
            myList = singletonList
            myAdapter.list = myList
            diff.dispatchUpdatesTo(myAdapter)

        }, TIMER)
    }

    companion object {

        const val TIMER = 5000L

        private var singletonList = initList()

        fun initList(): List<Number> {
            val currentList = mutableListOf<Number>()
            for (i in 0..25) {
                val num = Number(Random.nextLong(), Random.nextInt(1000000))
                currentList.add(num)
            }
            return currentList
        }
    }
}