package com.raywenderlich.listmaker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

// This class is a RECYCLER VIEW ADAPTER.
// How is that so?
// I make it accept a TaskList (which contains details) which is of type RecycleViewAdapter. That is how we decide
// to make this class BE a RecyclerViewAdapter (I think)
class ListItemsRecyclerViewAdapter (var list: TaskList) :
    RecyclerView.Adapter<ListItemViewHolder>() {

    /**
     * These 3 override functions below ARE mandatory to exist in order to
     * implement the RecyclerView.Adapter.
     */

    // I noticed BOTH functions and classes can be of a certain type.
    // In this case, they are of type "ListItemViewHolder".
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        // How to create a VIEW from the layout? Use a "LayoutInflater".
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_view_holder, parent, false)

        // Some thoughts, think of "Layouts" are schemas that are deflated,
        // and they only come to life via using a "LayoutInflater" object
        // and specifying what layout to use (which is always found in the "R" object).

        return ListItemViewHolder(view);
    }

    override fun getItemCount(): Int {
        // This method determines HOW MANY items you want to display in your list.
        // To display all, simply get the "length" ('size' is Kotlin's equivalent for length)
        return list.tasks.size
    }

    // Hook up the view inside the layout into the ViewHolder.
    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        if (holder != null) {
            holder.taskTextView.text = list.tasks[position]
        }
    }

    // What are the first steps to making an adapter?

}