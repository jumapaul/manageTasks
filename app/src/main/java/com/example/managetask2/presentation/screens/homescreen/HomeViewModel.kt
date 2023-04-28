package com.example.managetask2.presentation.screens.homescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.use_cases.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: TaskUseCase
): ViewModel() {

    var allTasks: MutableLiveData<List<TaskData>> = MutableLiveData()
    var tasksByDate: MutableLiveData<List<TaskData>> = MutableLiveData()
    var isImportant: MutableLiveData<List<TaskData>> = MutableLiveData()

    init {
        getAllScheduleTask()
        //getByDate("Today")
    }
    private fun getAllScheduleTask() = viewModelScope.launch {
        val response = useCase.getAllTasks()
        allTasks.value = response
    }

//    private fun getByDate(date: String) = viewModelScope.launch {
//        val dataByDate = useCase.getByDate(date)
//        tasksByDate.value = dataByDate
//    }

}