package com.example.composelist.taskInfo.view.screens.dialogs

import android.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.composelist.BaseDialog
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.viewModal.TaskListViewModel
import kotlinx.coroutines.delay

/**
 * Dialog asking user for a name for the task
 * @param isDialogOpen Flag marking if the dialog is open or closed
 */
@Composable
fun EditTaskNameDialog(
		task: Task,
		subTask: MutableState<Task?>? = null,
		isDialogOpen: MutableState<Boolean>,
		taskListViewModel: TaskListViewModel
) {

	val taskToEdit = subTask?.value ?: task


	var taskName by remember { mutableStateOf(TextFieldValue(taskToEdit.name)) }


	BaseDialog(title = "Edit Task Name", onDismiss = {
		// Dismiss the dialog when the user clicks outside the dialog or on the back
		isDialogOpen.value = false
	}, onSubmit = {
		if (taskName.text.trim().isNotEmpty()) {

			val cleanName = taskName.text.trim()

			// Update the the task in the taskList
			if(subTask?.value !== null){
				taskListViewModel.updateSubTaskName(task, taskToEdit, cleanName)
			}
			else {
				taskListViewModel.updateTaskName(taskToEdit, cleanName)
			}

			// for closing the dialog
			isDialogOpen.value = false
		}
	}, submitText = "Done") {

		// Used to have the TextField be focused when dialog is opned
		val focusRequester = remember { FocusRequester() }

		LaunchedEffect(key1 = Unit) {
			delay(200)
			focusRequester.requestFocus()
		}

		TextField(
				value = taskName,
				onValueChange = {
					taskName = it
				},
				modifier = Modifier
						.fillMaxWidth()
						.border(
								BorderStroke(
										width = 2.dp,
										color = colorResource(
												id = R.color.darker_gray
										)
								),
								shape = RoundedCornerShape(50)
						)
						.verticalScroll(state = rememberScrollState(), enabled = true)
						.heightIn(
								min = 50.dp,
								max = 100.dp
						)
						.focusRequester(focusRequester)
						.wrapContentHeight(),
				colors = TextFieldDefaults.textFieldColors(
						backgroundColor = Color.Transparent,
						focusedIndicatorColor = Color.Transparent,
						unfocusedIndicatorColor = Color.Transparent
				),
				keyboardOptions = KeyboardOptions(
						keyboardType = KeyboardType.Text,
						capitalization = KeyboardCapitalization.Sentences
				),
				placeholder = { Text(text = "My Task") },
				leadingIcon = {
					Icon(
							imageVector = Icons.Filled.Edit,
							contentDescription = "Edit icon",
							tint = MaterialTheme.colors.secondary,
							modifier = Modifier
									.width(20.dp)
									.height(20.dp)
					)
				},

				)
	}
}
