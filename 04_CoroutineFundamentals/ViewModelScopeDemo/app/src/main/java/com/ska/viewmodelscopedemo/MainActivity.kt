package com.ska.viewmodelscopedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityViewModel.users.observe(this, Observer {
            Log.d("TAG", "Users without builder: $it")
        })

        mainActivityViewModel.usersWithBuilder.observe(this, Observer {
            Log.d("TAG", "Users with builder: $it")
        })
    }
}