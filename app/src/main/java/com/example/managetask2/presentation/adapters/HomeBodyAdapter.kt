package com.example.managetask2.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.HomeBodyRecycleviewBinding

class HomeBodyAdapter: RecyclerView.Adapter<HomeBodyAdapter.HomeBodyViewHolder>() {

    lateinit var binding: HomeBodyRecycleviewBinding
    var item: MutableList<TaskData> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(data: List<TaskData>){
        item.clear()
        item.addAll(data)
        notifyDataSetChanged()
    }

    inner class HomeBodyViewHolder(binding: HomeBodyRecycleviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: TaskData){
            binding.tvHomeBody.text = data.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBodyViewHolder {
        binding = HomeBodyRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeBodyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeBodyViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }
}