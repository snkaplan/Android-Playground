package com.sk.roomdemo.repository

import com.sk.roomdemo.db.Subscriber
import com.sk.roomdemo.db.SubscriberDAO

class SubscriberRepository(private val subscriberDAO: SubscriberDAO) {
    val subscribers = subscriberDAO.getAll()

    suspend fun insertSubscriber(subscriber: Subscriber): Long{
        return subscriberDAO.insert(subscriber)
    }

    suspend fun updateSubscriber(subscriber: Subscriber): Int{
        return subscriberDAO.update(subscriber)
    }

    suspend fun deleteSubscriber(subscriber: Subscriber): Int{
       return subscriberDAO.delete(subscriber)
    }

    suspend fun deleteAllSubscribers(): Int{
        return subscriberDAO.deleteAll()
    }
}