package com.ska.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ska.recyclerviewdemo.data.Movie
import com.ska.recyclerviewdemo.databinding.ListItemBinding


class MRecyclerViewAdapter(
    private val movieList: List<Movie>,
    private val clickListener: (Movie) -> Unit,
) : RecyclerView.Adapter<MRecyclerViewAdapter.MViewHolder>() {
    private lateinit var itemBinding: ListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        itemBinding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return MViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(movieList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie, clickListener: (Movie) -> Unit,) {
            itemBinding.myTextView.text = movie.name
            view.setOnClickListener {
                clickListener(movie)
            }
        }
    }
}

