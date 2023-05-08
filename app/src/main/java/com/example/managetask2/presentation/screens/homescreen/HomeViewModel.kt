package com.example.managetask2.presentation.screens.homescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.use_cases.TaskUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: TaskUseCase,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

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

    fun getUserData(): DocumentReference? {
        val userId = firebaseAuth.currentUser?.uid

        return userId?.let { firebaseFirestore.collection("user").document(it) }

    }

    fun logOutUser(){
        firebaseAuth.signOut()
    }
}