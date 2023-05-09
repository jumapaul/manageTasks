package com.example.managetask2.presentation.screens.alltaskscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.use_cases.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllTaskViewModel @Inject constructor(
    private var useCase: TaskUseCase
): ViewModel() {

    var list: MutableLiveData<List<TaskData>> = MutableLiveData()
    var sortedItems: MutableLiveData<List<TaskData>> = MutableLiveData()

    fun deleteTask(taskData: TaskData) = viewModelScope.launch {
        useCase.deleteTask(taskData)
    }

    fun insertTask(taskData: TaskData) = viewModelScope.launch {
        useCase.addTask(taskData)
    }
    init {
        getAllTasks()
        getAllTasksSortedByDateTime()
    }

    private fun getAllTasks() = viewModelScope.launch {
        val response = useCase.getAllTasks()
        list.value = response

    }

    private fun getAllTasksSortedByDateTime() = viewModelScope.launch {
        val sortedTasks = useCase.getAllTasksSortedByDateTime()
        sortedItems.value = sortedTasks
    }
}