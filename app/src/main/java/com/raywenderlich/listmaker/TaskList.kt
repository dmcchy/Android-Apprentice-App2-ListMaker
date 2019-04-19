package com.raywenderlich.listmaker

import android.os.Parcel
import android.os.Parcelable

class TaskList (val name: String, val tasks: ArrayList<String> =
    ArrayList<String>()): Parcelable {
    // I need a way to save my list to my android device,
    // so this is where "Shared Preferences" comes into play.
    // "S.P" for short let's you have a key-value pair store that you can
    // store items to retrieve later.

    // The books says there are other alternatives to keep your data
    // that are more complex in the later chapters.

    // 1
    /**
     * Here you’re adding a second constructor (as opposed to the primary constructor in the class declaration) so
     * that a TaskList object can be created from a passed-in Parcel.
     *
     * // Double constructor?
     */
    constructor(source: Parcel): this(
        source.readString(),
        source.createStringArrayList()
    )

    override fun describeContents() = 0


    // 2
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeStringList(tasks)
    }

    // 3
    companion object CREATOR: Parcelable.Creator<TaskList> {

        // 4
        override fun createFromParcel(source: Parcel): TaskList = TaskList(source)

        override fun newArray(size: Int):  Array<TaskList?> = arrayOfNulls(size)
    }
}