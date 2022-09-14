package com.example.composelist.tasklist.view

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.ui.theme.ComposeListTheme

/**
 *  A row of action and info regarding a task
 */
@Composable
fun TaskRow(
		task: Task,
		onEditPress: (Task) -> Unit,
		onToggleTask: (Task) -> Unit,
		onDeleteTask: ((Task)-> Unit)? = null,
) {

	Row(
			modifier = Modifier
					.padding(horizontal = 10.dp)
					.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
	) {


		Checkbox(
				checked = task.isComplete, //value of the checkbox return from task list
				onCheckedChange = {
					onToggleTask(task)
				},
		)


		Spacer(modifier = Modifier.width(10.dp))

		Text(
				text = task.name,
				// check if task item is complete then task will be crossed out
				textDecoration = if (task.isComplete) {
					TextDecoration.LineThrough
				} else {
					TextDecoration.None
				},
				modifier = Modifier.fillMaxWidth(0.7f),
				style = MaterialTheme.typography.body2,

				)


		if(onDeleteTask === null){
			Spacer(modifier = Modifier.fillMaxSize(0.5f))
		}

		Row() {
			Icon(

					imageVector = Icons.Filled.Edit, contentDescription = "Edit",
					modifier = Modifier
							.clickable {
								onEditPress(task)
							}
							.scale(1.2f),

					)

			Spacer(modifier = Modifier.fillMaxSize(0.4f))

			if (onDeleteTask !== null) {
				Icon(

						imageVector = Icons.Filled.Delete, contentDescription = "Delete",

						modifier = Modifier
								.clickable {
									onDeleteTask(task)
								}
								.scale(1.2f),


						)
			}
		}
	}

}

@Preview(
		name = "Light Mode",
		showBackground = true,
		device = Devices.PIXEL
)
@Preview(
		uiMode = Configuration.UI_MODE_NIGHT_YES,
		showBackground = true,
		name = "Dark Mode",
		device = Devices.PIXEL
)
@Composable
fun TaskPreview() {
	ComposeListTheme {
		Column() {
			// Actual list
			TaskRow(task = Task(name = "Test Example"), onEditPress = {}, onToggleTask = {},
					onDeleteTask = {})

			TaskRow(task = Task(
					name = "Test Example " +
						   "complete", isComplete = true
			), onEditPress = {}, onToggleTask = {},
					onDeleteTask = {})

			TaskRow(task = Task(
					name = "Test Example complete lonansjfknasjknsakgnjgskanj asndklmsadk",
					isComplete = true
			), onEditPress = {}, onToggleTask = {},
					onDeleteTask = {})

			TaskRow(task = Task(name = "No Delete"), onEditPress = {}, onToggleTask = {},
					onDeleteTask = null)
		}

	}
}