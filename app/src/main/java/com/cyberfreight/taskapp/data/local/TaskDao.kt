package com.cyberfreight.taskapp.data.local

import androidx.room.*
import com.cyberfreight.taskapp.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY id ASC")
    fun getAllTasks(): Flow<List<Task>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<Task>)
    
    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()
    
    @Query("SELECT COUNT(*) FROM tasks")
    suspend fun getTaskCount(): Int
} 