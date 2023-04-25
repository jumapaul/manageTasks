package com.example.managetask2.presentation.adapters.recycleviewadapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.TagsRecycleviewBinding
import com.example.managetask2.databinding.TaskRecycleviewBinding

class TagsRecycleViewAdapter : RecyclerView.Adapter<TagsRecycleViewAdapter.TagsViewHolder>() {

    lateinit var binding: TagsRecycleviewBinding
    var tags: MutableList<String> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addTags(taskData: List<String>) {
        tags.clear()
        tags.addAll(taskData)
        notifyDataSetChanged()
    }

    inner class TagsViewHolder(binding: TagsRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskData: String) {
            binding.cpTag.text = taskData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        binding = TagsRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TagsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.bind(tags[position])
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}