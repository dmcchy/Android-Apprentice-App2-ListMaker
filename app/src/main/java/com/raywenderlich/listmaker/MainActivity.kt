package com.raywenderlich.listmaker

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // "lateinit" = this item is going to be created sometime in the "future". hmmm for optimization purposes I guess...
    lateinit var listsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Set listsRecyclerView by referencing the ID of the RecyclerView you set up in your layout
        listsRecyclerView = findViewById<RecyclerView>(R.id.lists_recyclerview)

        // Let the RecyclerView know what kind of layout you want to present your items in.
        // This is similar to the Layouts you can use with your XML layouts, and you’ll need something to arrange
        // your items in a linear format.
        // The LinearLayoutManager will work perfectly for this.
        // You also pass in the Activity so the layout manager can access its Context.
        listsRecyclerView.layoutManager = LinearLayoutManager(this)

        // This lets the RecyclerView know what to use as its adapter.
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
