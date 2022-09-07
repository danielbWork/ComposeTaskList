package com.example.composelist.tasklist.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	@ColumnInfo(name = "name")
	var name: String,
	@ColumnInfo(name = "is_complete")
	var isComplete: Boolean,
//	@Ignore
//	var subTasks: List<Task>? = null
)
