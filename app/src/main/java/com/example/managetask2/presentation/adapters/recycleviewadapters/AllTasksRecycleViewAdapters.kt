package com.example.managetask2.presentation.adapters.recycleviewadapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.TaskRecycleviewBinding

class AllTasksRecycleViewAdapters :
    RecyclerView.Adapter<AllTasksRecycleViewAdapters.AllTasksViewHolder>() {
    lateinit var binding: TaskRecycleviewBinding

    var tasks: MutableList<TaskData> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addTask(taskData: List<TaskData>) {
        tasks.clear()
        tasks.addAll(taskData)
        notifyDataSetChanged()
    }

    inner class AllTasksViewHolder(binding: TaskRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskData: TaskData) {
            binding.apply {
                tvTaskTitle.text = taskData.title
                tvDescription.text = taskData.description
                tvTasksDate.text = taskData.date
                tvTasksTime.text = taskData.time
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTasksViewHolder {
        binding = TaskRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllTasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllTasksViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}