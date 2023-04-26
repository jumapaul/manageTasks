package com.example.managetask2.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources.Theme
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.presentation.component_data.CategoryData
import com.example.managetask2.databinding.CategoryRecycleviewBinding

class CategoryAdapter(var categories: ArrayList<CategoryData>, val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    lateinit var binding: CategoryRecycleviewBinding

    private val allTask: MutableList<TaskData> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<TaskData>){
        allTask.clear()
        allTask.addAll(data)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(binding: CategoryRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var currentPosition = -1
        var currentCategory: CategoryData? = null
        private val categoryName = binding.tvCategory
        private val categoryIcon = binding.ivGroupIcon
        private val card = binding.clLayout
        fun bind(categoryData: CategoryData, position: Int, data: List<TaskData>) {
            
            categoryName.text = categoryData.categoryName
            categoryIcon.setImageResource(categoryData.imageCategoryId)
            card.setBackgroundResource(categoryData.backgroundColor)
            binding.tvNumber.text = data.size.toString()

            this.currentPosition = position
            this.currentCategory = categoryData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding =
            CategoryRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category, position, allTask)

        val context = holder.itemView.context
        val displayMetrics = context.resources.displayMetrics
        val highPixel = (displayMetrics.heightPixels * (category.height / 100f))

    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
