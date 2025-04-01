package com.example.mobile1project.tempconv.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mobile1project.R

class tempconvViewModel:ViewModel() {
    var temp = MutableStateFlow("")
    private val _tempResult= MutableStateFlow<Float?>(null)
    val tempResult = _tempResult.asStateFlow()

    fun convertTemp(context:Context) {
        val tempValue = temp.value.toFloatOrNull()

        if (tempValue != null) {
            _tempResult.value = (tempValue * (9f / 5)) + 32
        } else {
            _tempResult.value = null
            context.getString(R.string.invalid_input)
        }
    }
}