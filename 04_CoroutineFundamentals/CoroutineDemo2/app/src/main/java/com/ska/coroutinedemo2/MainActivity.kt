package com.ska.coroutinedemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

/**
 * Assuming  we have two remote stores. We are getting stock count from both and combine them 1 result.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * In this way user will wait 16 seconds
         */
        CoroutineScope(Dispatchers.IO).launch{
            Log.i("TAG", "Calculation started ")
            val firstStock = getFirstStock()
            val secondStock = getSecondStock()
            val total = firstStock + secondStock
            Log.i("TAG", "Total stock: $total ")
        }

        /**
         * In this way two function will process parallel and user will wait only 10 seconds.
         */
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("TAG", "Calculation started ")
            val firstStock = async { getFirstStock() }
            val secondStock = async { getSecondStock() }
            val total = firstStock.await() + secondStock.await()
            Log.i("TAG", "Total stock: $total ")
        }

        /**
         * In this example we gave contexts of each async operation
         */
        CoroutineScope(Dispatchers.Main).launch {
            Log.i("TAG", "Calculation started ")
            val firstStock = async(Dispatchers.IO) { getFirstStock() }
            val secondStock = async(Dispatchers.IO) { getSecondStock() }
            val total = firstStock.await() + secondStock.await()
            Log.i("TAG", "Total stock: $total ")
        }
    }

    private suspend fun getFirstStock(): Int {
        delay(10000)
        Log.i("TAG", "First stock returning")
        return 60900
    }

    private suspend fun getSecondStock(): Int {
        delay(6000)
        Log.i("TAG", "First stock returning")
        return 33499
    }

}

