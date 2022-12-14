package com.example.composelist.taskInfo.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.composelist.taskInfo.view.screens.dialogs.EditTaskNameDialog
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.view.screens.TaskListScreen
import com.example.composelist.tasklist.view.screens.dialogs.DeleteTaskDialog
import com.example.composelist.tasklist.viewModal.TaskListViewModel


/**
 * The list for displaying subtasks
 */
@Composable
fun SubTaskList(
		task: Task,
		taskListViewModel: TaskListViewModel,
		isAddingSubtask: MutableState<Boolean>
) {

	if (task.subTasks.isEmpty()) {
		AddSubTaskButton(isAddingSubtask);
		return
	}

	val isEditingSubtask = remember { mutableStateOf(false) }
	val taskForDeleting = remember { mutableStateOf<Task?>(null) }
	val subTaskToChange = remember { mutableStateOf<Task?>(null) }

	if (subTaskToChange.value !== null) {

		when {

			isEditingSubtask.value -> EditTaskNameDialog(
					task = task,
					subTask = subTaskToChange,
					isDialogOpen = isEditingSubtask,
					taskListViewModel = taskListViewModel

			)

			taskForDeleting.value !== null -> DeleteTaskDialog(
					task = taskForDeleting,
					subTask = subTaskToChange,
					taskListViewModel = taskListViewModel
			)

		}

	}

	Column(
			modifier = Modifier
					.fillMaxHeight()
					.fillMaxWidth()
	) {
		Row(
				modifier = Modifier
						.fillMaxWidth()
						.padding(5.dp)
						.padding(bottom = 10.dp)
		) {
			Text(text = "Sub Tasks:", style = MaterialTheme.typography.subtitle1)
			Spacer(Modifier.weight(1f))
			Icon(

					imageVector = Icons.Outlined.Add, contentDescription = "Add Task to item",
					modifier = Modifier
							.clickable {
								isAddingSubtask.value = true
							}
							.scale(1.2f)

					)

		}

		TaskListScreen(task.subTasks,
				onEditPress = { subtask ->
					subTaskToChange.value = subtask
					isEditingSubtask.value = true
				},
				onToggleTask = { subtask ->
					taskListViewModel.toggleSubTask(task, subtask)

				},
				onDeletePress = { subtask ->
					subTaskToChange.value = subtask
					taskForDeleting.value = task
				})
	}

}

/**
 *  Button shown when user hasn't added any subtasks yet
 */
@Composable
fun AddSubTaskButton(
		isAddingSubtask: MutableState<Boolean>
) = Box(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)) {
	Button(

			shape = RoundedCornerShape(50.dp),
			modifier = Modifier
					.fillMaxWidth()
					.defaultMinSize(minHeight = 50.dp),
			onClick = {
				isAddingSubtask.value = true
			},
	) {
		Text(text = "Add Subtask")
	}
}


