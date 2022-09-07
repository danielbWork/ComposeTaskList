package com.example.composelist.tasklist.modal

//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey

//@Entity(tableName = "tasks")
data class Task(
//	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
//	@ColumnInfo(name = "name")
	var name: String,
//	@ColumnInfo(name = "isComplete")
	var isComplete: Boolean,
	var subTasks: List<Task>? = null
)
