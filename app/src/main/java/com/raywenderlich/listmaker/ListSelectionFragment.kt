package com.raywenderlich.listmaker

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class ListSelectionFragment : Fragment(),
    ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    override fun listItemClicked(list: TaskList) {
        listener?.onListItemClicked(list)
    }

    // Initialize this on first lifecycle "onAttach" because fragments do not
    // extend from context.
    lateinit var listDataManager: ListDataManager
    lateinit var listsRecyclerView: RecyclerView

    // 1
    // Is a reference to the object that call's this Fragment interface.
    // MainActivity will be the one implementing this interface.
    private var listener: OnListItemFragmentInteractionListener? = null

    interface OnListItemFragmentInteractionListener {
        fun onListItemClicked(list: TaskList)
    }

    // 2
    companion object {
        // This inner class method "newInstance" will be called by outside classes
        // who will create this fragment.
        fun newInstance(): ListSelectionFragment {
            val fragment = ListSelectionFragment()
            return fragment
        }
    }

    // 3
    override fun onAttach(context: Context) {
        // First lifecycle run by the fragment
        super.onAttach(context)

        if (context is OnListItemFragmentInteractionListener) {
            listener = context
            // Initialize list data manager here instead.
            listDataManager = ListDataManager(context)
        } else {
            throw RuntimeException("${context.toString()} must implement OnFragmentInteractionListener")
        }
    }

    // 4
    override fun onCreate(savedInstanceState: Bundle?) {
        // 2nd lifecycle to be created.
        super.onCreate(savedInstanceState)
    }

    // Runs when the Activity attached to finished running "onCreate"
    // When another weird lifecycle
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var lists = listDataManager.readLists()
        view?.let {
            listsRecyclerView = it.findViewById<RecyclerView>(R.id.lists_recyclerview)
            listsRecyclerView.layoutManager = LinearLayoutManager(activity)
            listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
        }
    }

    // 5
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 3rd lifecycle
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_selection, container, false)
    }

    // 6
    override fun onDetach() {
        super.onDetach()
        // Final lifecycle on detach
        listener = null
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)

        val recyclerAdapter = listsRecyclerView.adapter as
                ListSelectionRecyclerViewAdapter
        recyclerAdapter.addList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        val lists = listDataManager.readLists()
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
    }

}
