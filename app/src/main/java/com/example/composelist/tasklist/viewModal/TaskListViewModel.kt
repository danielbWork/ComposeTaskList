package com.example.composelist.tasklist.viewModal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.modal.database.TaskDatabase
import com.example.composelist.tasklist.modal.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View Model in charge of handleing a single task in the the task list
 */
class TaskListViewModel(application: Application) : AndroidViewModel(application) {

	val taskList: LiveData<List<Task>>
	private val repository: TaskRepository

	init {
		val taskDao = TaskDatabase.getInstance(application).taskDao()
		repository = TaskRepository(taskDao)
		taskList = repository.tasks
	}

	/**
	 * Add the task to the list
	 */
	fun addTask(task: Task) {
		viewModelScope.launch(Dispatchers.IO){
			repository.addTask(task)
		}
	}

	/**
	 * Removes the task from the list
	 */
	fun removeTask(task: Task) {
		viewModelScope.launch(Dispatchers.IO){
			repository.deleteTask(task)
		}	}

	/**
	 *  Updates the task's completion status
	 */
	fun toggleTaskCompletion(task: Task){

		viewModelScope.launch(Dispatchers.IO){
			repository.updateTask(task.copy(isComplete = !task.isComplete))
		}
	}

	/**
	 *  Gets the value of the task with the given id
	 */
	fun getTaskByID(id: Int): LiveData<Task> {

		return repository.getByID(id)
	}

	/**
	 * Updated the task name with the new one
	 */
	fun updateTaskName(task: Task, taskName: String) {
		viewModelScope.launch(Dispatchers.IO){
			repository.updateTask(task.copy(name = taskName))
		}
	}

	/**
	 * Adds the new subtask to the task
	 */
	fun addSubTask(task: Task, subTask: Task) {
		viewModelScope.launch(Dispatchers.IO){
			val newList = task.subTasks.toMutableList()
			newList.add(subTask)
			
			repository.updateTask(task.copy( subTasks = newList.toList()))
		}


	}

	/**
	 * Toggles a subtask's completion status and updates the db
	 */
	fun toggleSubTask(task: Task, subtask: Task) {
		viewModelScope.launch(Dispatchers.IO){

			val newSubtasks = task.subTasks.toMutableList()
			val index = task.subTasks.indexOf(subtask)
			newSubtasks[index] = subtask.copy(isComplete = !subtask.isComplete)

			repository.updateTask(task.copy(subTasks = newSubtasks))
		}
	}

	/**
	 * Removes a subtask from the given task
	 */
	fun removeSubTask(task: Task, subtask: Task) {
		viewModelScope.launch(Dispatchers.IO){

			val newSubtasks = task.subTasks.toMutableList()
			newSubtasks.remove(subtask)

			repository.updateTask(task.copy(subTasks = newSubtasks))
		}
	}

	/**
	 * Removes a subtask from the given task
	 */
	fun updateSubTaskName(task: Task, subtask: Task, name: String) {
		viewModelScope.launch(Dispatchers.IO){


			val newSubtasks = task.subTasks.toMutableList()
			val index = task.subTasks.indexOf(subtask)
			newSubtasks[index] = subtask.copy(name = name)

			repository.updateTask(task.copy(subTasks = newSubtasks))
		}
	}


}