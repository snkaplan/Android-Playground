package com.ska.lifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import com.ska.lifecycledemo.ui.main.MainFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        lifecycleScope.launch{
            delay(5000)
            findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
            delay(5000)
            findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
        }
        /**
         * Launches only when fragment is created
         */
        lifecycleScope.launchWhenCreated {
            Log.i("TAG", "Launch when created")
        }
        /**
         * Launches only when fragment is on started
         */
        lifecycleScope.launchWhenStarted {
            Log.i("TAG", "Launch when started")
        }
        /**
         * Launches only when fragment is on resumed
         */
        lifecycleScope.launchWhenResumed {
            Log.i("TAG", "Launc when resumed")
        }
    }
}