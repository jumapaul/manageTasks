package com.example.managetask2.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.managetask2.R
import com.example.managetask2.presentation.component_data.CategoryData
import com.example.managetask2.databinding.CategoryRecycleviewBinding

class CategoryAdapter() :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    lateinit var binding: CategoryRecycleviewBinding

    private val allTask: MutableList<CategoryData> = ArrayList()

    var navController: NavController? = null

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<CategoryData>) {
        allTask.clear()
        allTask.addAll(data)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(binding: CategoryRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val categoryName = binding.tvCategory
        private val categoryIcon = binding.ivGroupIcon
        private val card = binding.clLayout
        fun bind(categoryData: CategoryData) {
            categoryName.text = categoryData.categoryName
            categoryIcon.setImageResource(categoryData.imageCategoryId)
            card.setBackgroundResource(categoryData.backgroundColor)

            binding.apply {
                tvNumber.text = categoryData.itemSize.toString()
                cvCard.setOnClickListener {
                    navController = Navigation.findNavController(binding.root)
                    navController!!.navigate(R.id.action_homeScreen_to_allTaskScreen)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding =
            CategoryRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.bind(allTask[position])

    }

    override fun getItemCount(): Int {
        return allTask.size
    }
}
