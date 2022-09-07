package com.example.composelist.tasklist.modal.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.composelist.tasklist.modal.Task

@Dao
interface TaskDao {

	@Query("SELECT * FROM tasks")
	fun getAll(): LiveData<List<Task>>

	@Query("SELECT * FROM tasks where id = :id")
	fun getByID(id: Int): LiveData<Task>

	@Insert
	suspend fun insert(task:Task)

	@Update
	suspend fun update(task:Task)

	@Delete
	suspend fun delete(task:Task)

	@Query("DELETE FROM tasks")
	suspend fun deleteAllTasks()

}