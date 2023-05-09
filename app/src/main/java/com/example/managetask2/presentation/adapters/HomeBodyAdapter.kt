package com.example.managetask2.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.managetask2.R
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.HomeBodyRecycleviewBinding

class HomeBodyAdapter(val context: Context): RecyclerView.Adapter<HomeBodyAdapter.HomeBodyViewHolder>() {

    lateinit var binding: HomeBodyRecycleviewBinding
    var item: MutableList<TaskData> = ArrayList()
    var navController: NavController? = null


    @SuppressLint("NotifyDataSetChanged")
    fun addItem(data: List<TaskData>){
        item.clear()
        item.addAll(data)
        notifyDataSetChanged()
    }

    inner class HomeBodyViewHolder(binding: HomeBodyRecycleviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: TaskData){
            binding.tvHomeBody.text = data.title
            binding.tvHomeBody.setOnClickListener {
                val id = data.id
                val bundle = bundleOf("itemId" to id)
                navController = Navigation.findNavController(binding.root)
                navController!!.navigate(R.id.action_homeScreen_to_allTaskScreen, bundle)

            }
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