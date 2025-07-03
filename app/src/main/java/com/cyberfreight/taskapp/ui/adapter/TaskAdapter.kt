package com.cyberfreight.taskapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cyberfreight.taskapp.data.model.Task
import com.cyberfreight.taskapp.databinding.ItemTaskBinding

class TaskAdapter : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class TaskViewHolder(private val binding: ItemTaskBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(task: Task) {
            binding.apply {
                taskTitle.text = task.title
                taskDescription.text = "Task ID: ${task.id} | User ID: ${task.userId}"
                taskStatus.text = if (task.isCompleted) "Completed" else "Pending"
                taskStatus.setTextColor(
                    if (task.isCompleted) 
                        android.graphics.Color.GREEN 
                    else 
                        android.graphics.Color.RED
                )
                taskDate.text = "Task #${task.id}"
            }
        }
    }
    
    private class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
} 