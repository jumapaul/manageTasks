package com.example.managetask2.presentation.screens.alltaskscreen

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

    init {
        getAllTasks()
    }

    fun getAllTasks() = viewModelScope.launch {
        val response = useCase.getAllTasks()
        list.value = response

    }
}