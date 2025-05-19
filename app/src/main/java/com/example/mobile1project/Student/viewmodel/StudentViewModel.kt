package com.example.mobile1project.Student.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile1project.Student.model.Student
import com.example.mobile1project.Student.network.StudentApiService
import com.example.mobile1project.Student.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {

    private val apiService = StudentApiService.create()
    private val repository = StudentRepository(apiService)

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students = _students.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadStudents()
    }

    fun loadStudents() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.fetchStudents()
            result.onSuccess { studentList ->
                _students.value = studentList
                _errorMessage.value = null
            }.onFailure { exception ->
                _students.value = emptyList()
                _errorMessage.value = exception.message
            }
            _isLoading.value = false
        }
    }
}