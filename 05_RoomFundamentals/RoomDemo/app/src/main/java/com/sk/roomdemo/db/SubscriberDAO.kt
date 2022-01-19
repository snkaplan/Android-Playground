package com.sk.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subscriber: Subscriber): Long

    @Update
    suspend fun update(subscriber: Subscriber): Int

    @Delete
    suspend fun delete(subscriber: Subscriber): Int

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll(): Int

    // Room handles all background operations if method returns LiveData
    @Query("SELECT * FROM SUBSCRIBER_DATA_TABLE")
    fun getAll(): LiveData<List<Subscriber>>

}