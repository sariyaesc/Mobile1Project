package com.example.mobile1project.Student.repository


import com.example.mobile1project.Student.model.Student
import com.example.mobile1project.Student.network.StudentApiService
import retrofit2.HttpException
import java.net.UnknownHostException

class StudentRepository(private val apiService: StudentApiService) {

    suspend fun fetchStudents(): Result<List<Student>> {
        return try {
            val students = apiService.getStudents()
            Result.success(students)
        } catch (e: HttpException) {
            val message = when (e.code()) {
                404 -> "Error 404: Estudiantes no encontrados"
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