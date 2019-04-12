package com.raywenderlich.listmaker

class TaskList (val name: String, val tasks: ArrayList<String> =
    ArrayList<String>()) {
    // I need a way to save my list to my android device,
    // so this is where "Shared Preferences" comes into play.
    // "S.P" for short let's you have a key-value pair store that you can
    // store items to retrieve later.

    // The books says there are other alternatives to keep your data
    // that are more complex in the later chapters.

}