package com.raywenderlich.listmaker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.widget.EditText

class ListDetailActivity : AppCompatActivity() {

    // Declare my button in this object.
    lateinit var addTaskButton: FloatingActionButton

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

        // Hook a click listener to the new button. (configure the object's button)
        addTaskButton = findViewById<FloatingActionButton>(R.id.add_task_button)
        addTaskButton.setOnClickListener() {
            // We will be using a dialogue here too, similar to the main screen.
            showCreateTaskDialog()
        }
    }

    // Here I create the Dialogue modal that will prompt users to add a new task.
    private fun showCreateTaskDialog() {
        // Wonder what this EditText is?
        val taskEditText = EditText(this)

        // Config input type to text. (obvious I know, but hey - I'm still noob at this stuff)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        // Lots of method chaining in here.
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task, { dialog, _ ->
                val task = taskEditText.text.toString()
                list.tasks.add(task)

                // Inform the adapter that new entries have been added,
                // it's not like react that automatically refreshes.
                val recyclerAdapter = listItemsRecyclerView.adapter as
                        ListItemsRecyclerViewAdapter
                recyclerAdapter.notifyItemInserted(list.tasks.size)

                dialog.dismiss()
            })
            .create()
            .show()
    }
}
