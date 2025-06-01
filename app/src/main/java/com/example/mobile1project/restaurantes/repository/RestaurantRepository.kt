package com.example.mobile1project.restaurantes.repository


import com.example.mobile1project.restaurantes.model.Restaurant
import com.example.mobile1project.restaurantes.network.RestaurantApiService
import retrofit2.HttpException
import java.net.UnknownHostException

class RestaurantRepository(private val apiService: RestaurantApiService) {

    suspend fun fetchStudents(): Result<List<Restaurant>> {
        return try {
            val restaurants = apiService.getRestaurants()
            Result.success(restaurants)
        } catch (e: HttpException) {
            val message = when (e.code()) {
                404 -> "Error 404: Restaurantes no encontrados"
                500 -> "Error 500: Problema en el servidor"
                else -> "Error HTTP ${e.code()}"
            }
            Result.failure(Exception(message))
        } catch (e: UnknownHostException) {
            Result.failure(Exception("Sin conexión a Internet"))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.localizedMessage}"))
        }
    }
}