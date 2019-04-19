package com.raywenderlich.listmaker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class ListDetailActivity : AppCompatActivity() {

    // I am importing a recycler view to this page, this is the "main" object that is going to
    // rendered.
    lateinit var listItemsRecyclerView: RecyclerView

    // The purpose of this activity was just to show another screen. Fancy that.

    lateinit var list: TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        // Retrieve list passed as an "Extra"
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)
        title = list.name

        // Assign the "RecyclerView" to our object here.
        listItemsRecyclerView = findViewById<RecyclerView>(R.id.list_items_recyclerview)

        // hmm specifying an adapter that DOES NOT exist yet?
        // I'm going to create an adapter shortly (hmmm CURIOUS)
        listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)

        // This will be the view portion of my Recycler view then.
        listItemsRecyclerView.layoutManager = LinearLayoutManager(this)


    }
}
