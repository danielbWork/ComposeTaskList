package com.example.composelist.tasklist.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "tasks")
data class Task(
		@PrimaryKey(autoGenerate = true)
		val id: Int = 0,
		@ColumnInfo(name = "name")
		var name: String,
		@ColumnInfo(name = "is_complete")
		var isComplete: Boolean,
		@ColumnInfo(name = "creation_time", defaultValue = "1662977020089")
		val creationTime: Long = java.util.Calendar.getInstance().timeInMillis
//	@Ignore
//	var subTasks: List<Task>? = null
)
