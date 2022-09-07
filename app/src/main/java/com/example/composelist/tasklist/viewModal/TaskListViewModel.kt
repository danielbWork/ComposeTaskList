package com.example.composelist.tasklist.viewModal

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.composelist.tasklist.modal.Task

/**
 * View Model in charge of handleing a single task in the the task list
 */
class TaskListViewModel : ViewModel() {

	private var _taskList = mutableStateListOf<Task>()

	fun getTaskList() : List<Task>{
		return _taskList
	}

	fun addTask(task: Task) {
		_taskList.add(task)
	}

	fun removeTask(task: Task) {
		_taskList.remove(task)
	}

	/**
	 *  Updates the task's completion status
	 */
	fun toggleTaskCompletion(task: Task){

		val index = _taskList.indexOf(task)

		_taskList[index] = task.let {
			it.copy(
				id = it.id,
				name = it.name,
				subTasks = it.subTasks,
				isComplete = !it.isComplete


			)
		}
	}


}