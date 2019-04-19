package com.raywenderlich.listmaker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(),
    ListSelectionFragment.OnListItemFragmentInteractionListener {


    private var listSelectionFragment: ListSelectionFragment =
        ListSelectionFragment.newInstance()

    companion object {
        val INTENT_LIST_KEY = "list"
        val LIST_DETAIL_REQUEST_CODE = 123
    }

    // "lateinit" = this item is going to be created sometime in the "future". hmmm for optimization purposes I guess...
    // lateinit var listsRecyclerView: RecyclerView
    // val listDataManager: ListDataManager = ListDataManager(this)

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
        // listsRecyclerView.layoutManager = LinearLayoutManager(this)

        // This lets the RecyclerView know what to use as its adapter.
        // listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter()



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
        builder.setPositiveButton(positiveButtonTitle) { dialog, i ->

            val list = TaskList(listTitleEditText.text.toString())
            listSelectionFragment.addList(list)


            dialog.dismiss()
            showListDetail(list)
        }

        // 4
        builder.create().show()
    }

    private fun showListDetail(list: TaskList) {
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)

        listDetailIntent.putExtra(INTENT_LIST_KEY, list)

        // This line starts your detail Activity as intended; however, it also adds the expectation
        // that MainActivity.kt will hear back from ListDetailActivity.kt once it has finished
        // being onscreen. So this is inter-activity communication?
        // LIST_DETAIL_REQUEST_CODE tells me I am here back from this activitiy.
        startActivityForResult(listDetailIntent, LIST_DETAIL_REQUEST_CODE)
    }

    // This method will make this MainActivity receive the results of any
    // activity it starts.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Make sure request code is the same code you are expecting to get back from another
        // activity. (I think you don't even need this step for a rudimentary example)
        if (requestCode == LIST_DETAIL_REQUEST_CODE) {
            // Wow, this data.let is a shorthand for checking if an object is not
            // null, before execution.
            data?.let {
                // If it's the result you want, you UNWRAP THE DATA INTENT passed in,
                // update the listDataManager, then I assume the last step
                // called "updateLists()" will tell your recyclerView to refresh?
                listSelectionFragment.saveList(data.getParcelableExtra(INTENT_LIST_KEY))
            }
        }
    }


    override fun onListItemClicked(list: TaskList) {
        showListDetail(list)
    }
}
