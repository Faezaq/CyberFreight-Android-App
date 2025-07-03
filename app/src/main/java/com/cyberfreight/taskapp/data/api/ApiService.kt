package com.cyberfreight.taskapp.data.api

import com.cyberfreight.taskapp.data.model.Task
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTasks(): List<Task>
} 