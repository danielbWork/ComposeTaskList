package com.example.composelist.tasklist.modal.database

import androidx.room.TypeConverter
import com.example.composelist.tasklist.modal.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

	@TypeConverter
	fun fromTaskListToJson(Tasks: List<Task>) : String{
		return Gson().toJson(Tasks)
	}

	@TypeConverter
	fun fromGsonToTaskList(json: String) : List<Task>{
		val type = object : TypeToken<List<Task>>() {}.type

		val result: List<Task>? = Gson().fromJson(json,type)

		return result ?: listOf()
	}

}