package com.example.managetask2.presentation.adapters.expandableadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.managetask2.presentation.component_data.CategoryData
import com.example.managetask2.databinding.CategoryRecycleviewBinding

class CategoryAdapter(var categories: ArrayList<CategoryData>, val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    lateinit var binding: CategoryRecycleviewBinding

    inner class CategoryViewHolder(binding: CategoryRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var currentPosition = -1
        var currentCategory: CategoryData? = null
        val categoryName = binding.tvCategory
        val categoryIcon = binding.ivGroupIcon
        val card = binding.clLayout
        fun bind(categoryData: CategoryData, position: Int) {
            categoryName.text = categoryData.categoryName
            categoryIcon.setImageResource(categoryData.imageCategoryId)

            card.setBackgroundResource(categoryData.backgroundColor)

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
        holder.bind(category, position)

        val context = holder.itemView.context
        val displayMetrics = context.resources.displayMetrics
        val highPixel = (displayMetrics.heightPixels * (category.height / 100f))

    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
