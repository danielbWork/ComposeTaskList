package com.example.composelist.taskInfo.view

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import com.example.composelist.tasklist.modal.Task
import java.util.*

/**
 * Displays the date and time of the app being created
 */
@Composable
fun TaskTimeRow(time: Long) = Row(
		modifier = Modifier
				.fillMaxWidth()
				.height(IntrinsicSize.Min),
		verticalAlignment = Alignment.CenterVertically
) {

	val simpleDate = SimpleDateFormat("dd/M/yyyy HH:mm:ss", Locale.getDefault())
	val date = Date(time)

	Text(
			text = "Time Created: ${simpleDate.format(date)}",
			modifier = Modifier.fillMaxWidth(0.7f),
			style = MaterialTheme.typography.body2,

			)


}
