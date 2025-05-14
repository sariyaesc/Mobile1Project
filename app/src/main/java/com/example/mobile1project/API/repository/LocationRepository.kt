package com.example.mobile1project.API.repository

import com.example.mobile1project.API.model.Location
import com.example.mobile1project.API.network.LocationApiService


class LocationRepository(private val apiService: LocationApiService) {
    suspend fun fetchLocations(): List<Location> {
        return apiService.getLocations()
    }
}