package com.ska.recyclerviewdemo.data

data class Movie(val name: String, val nameOfDirector: String) {
    override fun toString(): String {
        return "Movie Name: $name - Director: $nameOfDirector"
    }
}