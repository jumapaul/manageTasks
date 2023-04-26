package com.example.managetask2.presentation.screens.addtaskscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.use_cases.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase
) : ViewModel() {
  //  var tasks: LiveData<List<TaskData>> = MutableLiveData()

    fun addTask(taskData: TaskData) = viewModelScope.launch(Dispatchers.IO) {
        taskUseCase.addTask(taskData)
    }


}