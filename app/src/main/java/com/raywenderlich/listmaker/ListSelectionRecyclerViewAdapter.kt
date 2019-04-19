package com.raywenderlich.listmaker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListSelectionRecyclerViewAdapter (private val lists : ArrayList<TaskList>,
                                        val clickListener: ListSelectionRecyclerViewClickListener) :
    // This RecycleView "Configuration" holds my Adapter
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    // Mock data
    // var listTitles = arrayOf("Shopping List", "Chores", "Android Tutorials")

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        if (holder != null) {
            holder.listPosition.text = (position + 1).toString()
            holder.listTitle.text = lists.get(position).name

            // On click listener.
            holder.itemView.setOnClickListener() {
                clickListener.listItemClicked(lists.get(position))
            }
        }
    }

    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: TaskList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {

        // Create a layout programatically is this like react,
        // that is merely given data, and renders it programmatically?
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder,
                parent,
                false)

        // Return your layout
        return ListSelectionViewHolder(view)
    }


    override fun getItemCount(): Int {
        return lists.size
    }

    fun addList(list: TaskList) {
        // 1
        lists.add(list)

        // 2
        notifyDataSetChanged()
    }
}