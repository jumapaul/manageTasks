package com.example.managetask2.presentation.component_data

import com.example.managetask2.R

data class CategoryData(
    val imageCategoryId: Int,
    val categoryName: String,
    val backgroundColor: Int,
    val height: Int
)

object Category {
    val categoryName = arrayListOf(
        "Scheduled", "Today", "Important", "All tasks"
    )
    val categoryIcon = arrayListOf(
        R.drawable.ic_baseline_schedule_24,
        R.drawable.ic_baseline_today,
        R.drawable.ic_baseline_important_24,
        R.drawable.ic_baseline_folder_24
    )

    val cardHeight = arrayListOf(
        180, 90, 180, 90
    )

    val backgroundColor = listOf(
        R.color.scheduled, R.color.today,
        R.color.important, R.color.all_task
    )

    var category: ArrayList<CategoryData>? = null
        get() {
            if (field != null) field

            field = ArrayList()

            for (i in categoryName.indices) {
                val name = categoryName[i]
                val icon = categoryIcon[i]
                val color = backgroundColor[i]
                val height = cardHeight[i]
                val category = CategoryData(icon, name, color, height)

                field!!.add(category)
            }
            return field
        }
}
