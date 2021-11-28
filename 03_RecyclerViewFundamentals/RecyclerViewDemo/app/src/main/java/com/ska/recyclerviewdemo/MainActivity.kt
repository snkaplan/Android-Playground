package com.ska.recyclerviewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ska.recyclerviewdemo.data.Movie
import com.ska.recyclerviewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val movieList = listOf(Movie("Matrix 1", "Lilly Wachowski and Lana Wachowski"),
        Movie("Infinity War", "Anthony Russo and Joe Russo"),
        Movie("End Game", "Anthony Russo and Joe Russo"),
        Movie("John Wick 2", "Chad Stahelski"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mRecyclerView.setBackgroundColor(Color.YELLOW)
        binding.mRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mRecyclerView.adapter = MRecyclerViewAdapter(movieList
        ) { selectedMovieItem: Movie -> listItemClicked(selectedMovieItem) }
    }

    private fun listItemClicked(movie: Movie) {
        Toast.makeText(this, "Movie Info: $movie", Toast.LENGTH_LONG).show()
    }
}