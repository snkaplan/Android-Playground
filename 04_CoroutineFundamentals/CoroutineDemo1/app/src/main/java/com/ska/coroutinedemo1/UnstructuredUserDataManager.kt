package com.ska.coroutinedemo1

import kotlinx.coroutines.*

class UnstructuredUserDataManager {
    suspend fun getTotalUserCount(): Int {
        var count = 0

        /**
         * Example of unstructured concurrency without async coroutine. Causes some unexpected exceptions!!
         */
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            count = 5
        }

        /**
         * Example of unstructured concurrency with async coroutine
         */
        val deferred = CoroutineScope(Dispatchers.IO).async {
            delay(3000)
            return@async 70
        }
        return count + deferred.await()
    }
}