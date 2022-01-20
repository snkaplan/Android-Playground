package com.sk.retrofitdemo.data


import com.google.gson.annotations.SerializedName

data class AlbumsItem(
    val id: Int,
    val title: String,
    val userId: Int
)