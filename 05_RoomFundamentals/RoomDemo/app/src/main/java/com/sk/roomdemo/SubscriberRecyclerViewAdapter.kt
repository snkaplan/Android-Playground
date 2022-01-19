package com.sk.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sk.roomdemo.databinding.ListItemBinding
import com.sk.roomdemo.db.Subscriber

class SubscriberRecyclerViewAdapter(private val clickListener: (Subscriber) -> Unit) :
    RecyclerView.Adapter<SubscriberViewHolder>() {
    private var subscribers: List<Subscriber> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent,false)
        return SubscriberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        holder.bind(subscribers[position], clickListener)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }

    fun setSubscriberList(subscribers: List<Subscriber>){
        this.subscribers = subscribers
    }


}

class SubscriberViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
        binding.listItemLayout.setOnClickListener{
            clickListener(subscriber)
        }
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
    }
}