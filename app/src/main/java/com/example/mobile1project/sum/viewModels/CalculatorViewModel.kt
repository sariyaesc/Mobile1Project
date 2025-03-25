package com.example.mobile1project.sum.viewModels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class CalculatorViewModel : ViewModel() {
    private var _result = mutableStateOf(0)
    val result: State<Int> get() = _result

    fun calculateSum(a: Int, b: Int) {
        _result.value = a + b
    }
}