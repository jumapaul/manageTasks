package com.example.managetask2.presentation.screens.FAQs

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.managetask2.R
import com.example.managetask2.data.entity.FAQs
import com.example.managetask2.databinding.FragmentHomeScreenBinding
import com.example.managetask2.databinding.FragmentQuestionsBinding
import com.example.managetask2.databinding.QuestionItemsBinding
import com.example.managetask2.presentation.adapters.FAQAdapter

class QuestionsFragment : Fragment() {

    lateinit var binding: FragmentQuestionsBinding
    lateinit var homeBinding: FragmentHomeScreenBinding
    lateinit var questionBinding: QuestionItemsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentQuestionsBinding.inflate(inflater)
        homeBinding = FragmentHomeScreenBinding.inflate(inflater)
        questionBinding = QuestionItemsBinding.inflate(inflater)

        binding.apply {
            ivExitFaq.setOnClickListener {
                val drawer = homeBinding.drawerLayout
                findNavController().navigateUp(drawerLayout = drawer)
            }
        }
        val questionAndAnswers = listOf(
            FAQs(
                "What is taskManager?",
                "In the above code, navController.navigate(R.id.navigation_drawer) navigates to the navigation drawer fragment using the ID of the navigation graph destination that represents the navigation drawer. If the navigation drawer is already on the back stack, this will pop the back stack back to the navigation drawer"
            ),
            FAQs(
                "What does task manager do?",
                "In the above code, navController.navigate(R.id.navigation_drawer) navigates to the navigation drawer fragment using the ID of the navigation graph destination that represents the navigation drawer. If the navigation drawer is already on the back stack, this will pop the back stack back to the navigation drawer"
            ),
            FAQs(
                "How to use taskManager",
                "In the above code, navController.navigate(R.id.navigation_drawer) navigates to the navigation drawer fragment using the ID of the navigation graph destination that represents the navigation drawer. If the navigation drawer is already on the back stack, this will pop the back stack back to the navigation drawer"
            ),
            FAQs(
                "How to plan your tasks",
                "In the above code, navController.navigate(R.id.navigation_drawer) navigates to the navigation drawer fragment using the ID of the navigation graph destination that represents the navigation drawer. If the navigation drawer is already on the back stack, this will pop the back stack back to the navigation drawer"
            ),
            FAQs(
                "How to remove tasks from task list",
                "In the above code, navController.navigate(R.id.navigation_drawer) navigates to the navigation drawer fragment using the ID of the navigation graph destination that represents the navigation drawer. If the navigation drawer is already on the back stack, this will pop the back stack back to the navigation drawer"
            )
        )

        setUpFAQSRecycleView(questionAndAnswers)

        return binding.root
    }

    private fun setUpFAQSRecycleView(item: List<FAQs>) {
        val myAdapter = FAQAdapter()
        myAdapter.add(item)
        binding.rvQandA.apply {
            hasFixedSize()
            adapter = myAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }


}