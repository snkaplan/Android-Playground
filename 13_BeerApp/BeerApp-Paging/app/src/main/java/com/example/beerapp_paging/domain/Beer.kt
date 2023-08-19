package com.example.beerapp_paging.domain

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    var imageUrl: String?
)