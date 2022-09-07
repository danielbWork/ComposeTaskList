package com.example.composelist.tasklist.viewModal

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class TaskListViewModalFactory(private val application: Application): ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return TaskListViewModel(application) as T
	}
}