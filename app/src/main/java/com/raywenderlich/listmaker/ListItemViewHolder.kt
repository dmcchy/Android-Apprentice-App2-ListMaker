package com.raywenderlich.listmaker

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView


/**
 * This code sets up the ListItemViewHolder constructor to pass in a View you can use to reference the
 * ViewHolders widgets. It also makes the Class implement the RecyclerView.ViewHolder(itemView) interface,
 * passing in the view you want to use.
 */

// This class is of type "RecyclerView.Viewholder" and it a accepts a "View" via the itemView parameter.
class ListItemViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val taskTextView = itemView.findViewById<TextView>(R.id.textview_task)
        as TextView
}