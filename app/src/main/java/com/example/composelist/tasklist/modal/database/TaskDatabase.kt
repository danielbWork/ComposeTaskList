package com.example.composelist.tasklist.modal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.composelist.tasklist.modal.Task

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

	abstract fun taskDao(): TaskDao

	companion object {

		private var INSTANCE: TaskDatabase? = null

		private val migration_1_2: Migration = object : Migration(1, 2) {
			override fun migrate(database: SupportSQLiteDatabase) {
				database.execSQL(
						"ALTER TABLE tasks ADD COLUMN creation_time INTEGER DEFAULT 1662977020089"
				)
			}

		}

		fun getInstance(context: Context): TaskDatabase {

			synchronized(this) {

				var instance = INSTANCE

				if (instance == null) {

					instance = Room.databaseBuilder(
							context.applicationContext,
							TaskDatabase::class.java,
							"task_list_database"
					).addMigrations(migration_1_2).fallbackToDestructiveMigration().build()

				}

				return instance
			}

		}

	}

}