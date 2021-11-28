package com.ska.coroutinedemo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.ska.coroutinedemo1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnCount.setOnClickListener {
            binding.tvCount.text = count++.toString()
        }
        binding.btnDownloadUserData.setOnClickListener {

            /**
             * CoroutineScope define the scope of the coroutine
                * GlobalScope is used to launch top-level coroutines which are operating on the whole application lifetime
                * We should use GlobalScope very rarely
             * Dispatchers defines the kind of thread where the coroutine runs.
                * Dispatcher.Main --> coroutine will run in main thread. Recommended structure is start
                    * coroutine in main thread then switch it to background
                * Dispatcher.IO  --> coroutine will run in background thread.
                    * Use this to work with database, network and work with files.
                * Dispatcher.Default --> is used for CPU intensive tasks
                    * use this for sorting a list of data or parsing JSON file
                * Dispatcher.Unconfined --> is a dispatcher used with GlobalScope
                    * This coroutine will run on the current thread but if it is suspended or resumed
                    * it will run on whichever thread the suspending function is running on. It is not recommended to use
             * Launch is coroutine builder. There are 4 type of builders.
                * Launch builder launches a new coroutine without blocking the current thread.
                    * This builder returns an instance of job which can be used as a reference. This builder only can return Job,
                    * it can not return any result of computation.
                * Async builder allows us to launch coroutines also launches a new coroutine without blocking current thread.
                    * This builder returns an instance of deferred of type of result. Actually deferred interface is an extension of Job interface
                    * So we can use it like we use job for things like cancelling coroutine
                    * We can get return value of deferred function calling it with await()
                * Produce builder  is for coroutines which stream of elements. This builder returns an instance of ReceiveChannel.
                    * RunBlocking builder launches a coroutine with blocking current thread. And it returns a result we can directly use.(Type T)
             */
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }
    }

    /**
     * Example of switching coroutine
     * In kotlin coroutines, whenever a coroutine is suspended, the current stack frame of the function is copied and saved in the memory.
     * When the function resumes after completing its task, the stack frame is copied back from where it was saved and starts running again
     */
    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Dispatchers.Main){
                binding.tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"

            }
        }
    }
}
