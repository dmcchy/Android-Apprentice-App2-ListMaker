package com.raywenderlich.listmaker

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // "lateinit" = this item is going to be created sometime in the "future". hmmm for optimization purposes I guess...
    lateinit var listsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Do some actions here.
        fab.setOnClickListener { view ->
            showCreateListDialog()
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

    private fun showCreateListDialog() {
        // Get string data from "strings.xml"
        // 1
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        // Configure the builder, make a dialogue with a text view.
        // 2
        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        // TYPE_CLASS_TEXT means a Edit
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        // 3
        builder.setPositiveButton(positiveButtonTitle, { dialog, i -> dialog.dismiss() })

        // 4
        builder.create().show()
    }
}
