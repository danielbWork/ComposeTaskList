package com.example.composelist.tasklist.modal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.composelist.tasklist.modal.Task

@Database(entities = [Task::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
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

		private val migration_2_5: Migration = object : Migration(2, 5) {
			override fun migrate(database: SupportSQLiteDatabase) {
				database.execSQL(
						"ALTER TABLE tasks ADD COLUMN subtasks TEXT DEFAULT ''"
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
					)
							.addMigrations(migration_1_2, migration_2_5)
							.fallbackToDestructiveMigration().build()

				}

				return instance
			}

		}

	}

}