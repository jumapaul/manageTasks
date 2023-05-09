package com.example.managetask2.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.managetask2.R
import com.example.managetask2.data.entity.FAQs
import com.example.managetask2.databinding.QuestionItemsBinding

class FAQAdapter : RecyclerView.Adapter<FAQAdapter.FAQsViewHolder>() {

    var items: MutableList<FAQs> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun add(faQs: List<FAQs>) {
        items.clear()
        items.addAll(faQs)
        notifyDataSetChanged()
    }

    inner class FAQsViewHolder(private val binding: QuestionItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(faQs: FAQs) {
            binding.apply {
                tvQuestion.text = faQs.title
                tvAnswer.text = faQs.answers

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQsViewHolder {
        val binding = QuestionItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.ivAnswerDropDown.setOnClickListener {

            if (binding.llAnswer.visibility == View.GONE) {
                binding.llAnswer.visibility = View.VISIBLE
                binding.ivAnswerDropDown.setImageResource(R.drawable.baseline_expand_less_24)

            } else {
                binding.llAnswer.visibility = View.GONE
                binding.ivAnswerDropDown.setImageResource(R.drawable.baseline_expand_more_24)
            }
        }

        return FAQsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FAQsViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}