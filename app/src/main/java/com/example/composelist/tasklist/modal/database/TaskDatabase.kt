package com.example.composelist.tasklist.modal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composelist.tasklist.modal.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

	abstract fun taskDao(): TaskDao

	companion object {

		private var INSTANCE: TaskDatabase? = null

		fun getInstance(context: Context): TaskDatabase {

			synchronized(this) {

				var instance = INSTANCE

				if (instance == null) {

					instance = Room.databaseBuilder(
						context.applicationContext,
						TaskDatabase::class.java,
						"task_list_database"
					).fallbackToDestructiveMigration().build()

				}

				return instance
			}

		}

	}

}