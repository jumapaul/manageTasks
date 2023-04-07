package com.example.managetask2.presentation.screens.addtaskscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.use_cases.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {
    var tasks: LiveData<List<TaskData>> = MutableLiveData()

    fun addTask(taskData: TaskData) = viewModelScope.launch(Dispatchers.IO) {
        addTaskUseCase.addTask(taskData)
    }


}