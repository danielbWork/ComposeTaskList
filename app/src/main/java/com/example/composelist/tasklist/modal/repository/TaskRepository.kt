package com.example.composelist.tasklist.modal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.modal.database.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

	val tasks = taskDao.getAll()

	suspend fun addTask(task: Task){
		taskDao.insert(task)
	}

	suspend fun updateTask(task: Task){
		taskDao.update(task)
	}

	suspend fun deleteTask(task: Task){
		taskDao.delete(task)
	}

	fun getByID(id : Int) : LiveData<Task> {
		return taskDao.getByID(id)
	}

	suspend fun deleteAllTasks(){
		taskDao.deleteAllTasks()
	}

}