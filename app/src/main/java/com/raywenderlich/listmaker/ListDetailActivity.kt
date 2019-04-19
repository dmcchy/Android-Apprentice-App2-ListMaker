package com.raywenderlich.listmaker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ListDetailActivity : AppCompatActivity() {

    // The purpose of this activity was just to show another screen. Fancy that.

    lateinit var list: TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        // Retrieve list passed as an "Extra"
        // 1
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)

        // 2
        title = list.name
    }
}
