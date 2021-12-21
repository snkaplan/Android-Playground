package com.ska.coroutinedemo1

import kotlinx.coroutines.*

class StructuredUserDataManager {
    var count = 0
    lateinit var deferred: Deferred<Int>
    suspend fun getTotalUserCount(): Int {
        /**
         * coroutineScope is different from CoroutineScope.
         * CoroutineScope provides us Unstructured concurrency but coroutineScope provides us structed concurrency.
         * coroutineScope provides us to wait all jobs.
         */
        coroutineScope {
            /**
             * If we don't define dispatcher.IO in here this launcher
             * launches a new coroutine in parent scope which is scope in MainActivity Dispatchers.Main
             */
            launch(Dispatchers.IO) {
                delay(3000)
                count = 50
            }

            deferred = async(Dispatchers.IO) {
                delay(1000)
                return@async 70
            }
        }
        return count + deferred.await()
    }
}