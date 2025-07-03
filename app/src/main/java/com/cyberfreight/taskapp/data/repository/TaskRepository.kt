package com.cyberfreight.taskapp.data.repository

import android.content.Context
import com.cyberfreight.taskapp.data.api.RetrofitClient
import com.cyberfreight.taskapp.data.local.AppDatabase
import com.cyberfreight.taskapp.data.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class TaskRepository(context: Context) {
    private val taskDao = AppDatabase.getDatabase(context).taskDao()
    private val apiService = RetrofitClient.apiService
    
    fun getTasks(): Flow<Result<List<Task>>> = flow {
        try {
            android.util.Log.d("TaskRepository", "Starting to fetch tasks")
            
            // Try to fetch fresh data from API first
            android.util.Log.d("TaskRepository", "Fetching from API")
            val apiTasks = apiService.getTasks()
            android.util.Log.d("TaskRepository", "API returned ${apiTasks.size} tasks")
            taskDao.insertTasks(apiTasks)
            emit(Result.success(apiTasks))
            
        } catch (e: IOException) {
            android.util.Log.e("TaskRepository", "Network error: ${e.message}")
            // Network error - return cached data if available
            val cachedTasks = taskDao.getAllTasks()
            cachedTasks.collect { tasks ->
                if (tasks.isNotEmpty()) {
                    android.util.Log.d("TaskRepository", "Emitting ${tasks.size} cached tasks after network error")
                    emit(Result.success(tasks))
                } else {
                    emit(Result.failure(e))
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("TaskRepository", "Error: ${e.message}")
            // Try to get cached data as fallback
            try {
                val cachedTasks = taskDao.getAllTasks()
                cachedTasks.collect { tasks ->
                    if (tasks.isNotEmpty()) {
                        android.util.Log.d("TaskRepository", "Emitting ${tasks.size} cached tasks after exception")
                        emit(Result.success(tasks))
                    } else {
                        emit(Result.failure(e))
                    }
                }
            } catch (cacheException: Exception) {
                android.util.Log.e("TaskRepository", "Cache error: ${cacheException.message}")
                emit(Result.failure(e))
            }
        }
    }
    
    suspend fun refreshTasks(): Result<List<Task>> {
        return try {
            val tasks = apiService.getTasks()
            taskDao.insertTasks(tasks)
            Result.success(tasks)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getCachedTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }
    
    suspend fun hasCachedData(): Boolean {
        return taskDao.getTaskCount() > 0
    }
} 