package com.raywenderlich.listmaker

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

// Previously View?, changed to "View" only to remove the mismatch error.
class ListSelectionViewHolder (itemView: View) :
    // This configuration of RecyclerView "ViewHolders" holds my "views defined" lol
    RecyclerView.ViewHolder(itemView) {

    val listPosition = itemView?.findViewById<TextView>(R.id.itemNumber)
            as TextView

    val listTitle = itemView?.findViewById<TextView>(R.id.itemString)
            as TextView
}