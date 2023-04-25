package com.example.managetask2.presentation.adapters.recycleviewadapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.managetask2.R
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.FragmentAddTaskScreenBinding
import com.example.managetask2.databinding.TaskRecycleviewBinding
import com.example.managetask2.presentation.component_data.TagsData
import com.example.managetask2.presentation.screens.alltaskscreen.AllTaskViewModel

class AllTasksRecycleViewAdapters :
    RecyclerView.Adapter<AllTasksRecycleViewAdapters.AllTasksViewHolder>() {
    lateinit var binding: TaskRecycleviewBinding
    lateinit var addTaskScreenBinding: FragmentAddTaskScreenBinding
    private val tagsRecycleViewAdapter: TagsRecycleViewAdapter by lazy { TagsRecycleViewAdapter() }

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
            setUpTagsRecycleView(taskData.tags.orEmpty())
            binding.apply {
                tvTaskTitle.text = taskData.title
                tvDescription.text = taskData.description
                tvTasksDate.text = taskData.date
                tvTasksTime.text = taskData.time
                if (taskData.isImportant) {
                    binding.ivImportant.setImageResource(R.drawable.ic_baseline_important_24)
                }

                Glide.with(binding.root).load(taskData.imageUrl)
                    .into(binding.ivImageDescription)

                if (taskData.imageUrl?.isNotEmpty() == true) {
                    binding.llShowImage.visibility = View.VISIBLE

                }

                when(taskData.category){
                    "BUSINESS" -> {
                        binding.allTasksView.setBackgroundResource(R.color.dark_tortoise)
                    }
                    "HEALTH" -> {
                        binding.allTasksView.setBackgroundResource(R.color.orange)
                    }
                    "ENTERTAINMENT" -> {
                        binding.allTasksView.setBackgroundResource(R.color.light_purple)
                    }
                    "HOME" -> {
                        binding.allTasksView.setBackgroundResource(R.color.gainsboro)
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTasksViewHolder {
        binding = TaskRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        addTaskScreenBinding =
            FragmentAddTaskScreenBinding.inflate(LayoutInflater.from(parent.context))

        return AllTasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllTasksViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
    fun setUpTagsRecycleView(tags:List<String>){
        tagsRecycleViewAdapter.addTags(tags)
        binding.rvTags.apply {
            hasFixedSize()
            adapter = tagsRecycleViewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}