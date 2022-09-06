package com.example.composelist.modal

data class Task(val id: String, var name : String, var isComplete: Boolean, var subTasks: List<Task>? = null)
