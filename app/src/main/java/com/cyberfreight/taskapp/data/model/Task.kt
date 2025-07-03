package com.cyberfreight.taskapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    val id: Int,
    val title: String,
    @SerializedName("completed")
    val isCompleted: Boolean,
    val userId: Int
) 