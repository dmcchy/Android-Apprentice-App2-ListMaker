package com.raywenderlich.listmaker

import android.content.Context
import android.preference.PreferenceManager

class ListDataManager (private val context: Context) {

    fun saveList(list: TaskList) {
        // 1
        // Get default preferences, and enable editing via calling ".edit()"
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()

        // 2
        //
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())

        // 3
        sharedPreferences.apply()
    }

    fun readLists(): ArrayList<TaskList> {
        // 1
        // Grab a reference to the default SharedPreferences file.
        // This time, you don’t request a SharedPreferences.
        // Editor object since you only need to read from the file, not write to it.
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        // 2
        // Call sharedPreferences.all to get the contents of your SharedPreferences file as a Map.
        val sharedPreferenceContents = sharedPreferences.all

        // 3
        // Create an empty ArrayList of type TaskList.
        // You’ll use this to store the lists you retrieve from SharedPreferences.
        val taskLists = ArrayList<TaskList>()

        // 4
        // Iterate over all the items in the Map you received from SharedPreferences using a for loop.
        // In each iteration, take the value of the object and attempt to cast it to a HashSet<String>.
        // Recall from the SaveList earlier that you could not store a TaskList object directly as a string,
        // so we had converted that object into a HashSet. You perform the reverse to retrieve an object
        // back. Then recreate the list object by passing the key of the sharedPreference object as
        // the name of the TaskList and then convert the HashSet into an ArrayList of tasks back
        // to a structured object.
        for (taskList in sharedPreferenceContents) {
            val itemsHashSet = taskList.value as HashSet<String>
            val list = TaskList(taskList.key, ArrayList(itemsHashSet))

            // 5
            // Finally, add your newly reconstructed list into the empty ArrayList you created earlier.
            taskLists.add(list)
        }

        return taskLists
    }
}