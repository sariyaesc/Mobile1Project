package com.example.mobile1project.restaurantes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile1project.restaurantes.model.Restaurant
import com.example.mobile1project.restaurantes.repository.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class RestaurantViewModel(private val repository: RestaurantRepository) : ViewModel() {
    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadRestaurants() {
        viewModelScope.launch {
            val result = repository.fetchStudents()
            result.onSuccess {
                _restaurants.value = it
            }.onFailure {
                _error.value = it.message
            }
        }
    }

    fun getRestaurantByName(name: String): Restaurant? {
        return _restaurants.value.find { it.name == name }
    }
}