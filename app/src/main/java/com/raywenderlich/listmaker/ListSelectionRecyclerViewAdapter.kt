package com.raywenderlich.listmaker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListSelectionRecyclerViewAdapter :
    // This RecycleView "Configuration" holds my Adapter
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        if (holder != null) {
            holder.listPosition.text = (position + 1).toString()
            holder.listTitle.text = listTitles[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {

        // Create a layout programatically is this like react,
        // that is merely given data, and renders it programmatically?
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.list_selection_view_holder,
                parent,
                false)

        // Return your layout
        return ListSelectionViewHolder(view)
    }


    // Mock data
    var listTitles = arrayOf("Shopping List", "Chores", "Android Tutorials")

    override fun getItemCount(): Int {
        return listTitles.size
    }





}