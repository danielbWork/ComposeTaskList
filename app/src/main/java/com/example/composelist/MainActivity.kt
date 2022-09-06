package com.example.composelist

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelist.modal.Task
import com.example.composelist.ui.theme.ComposeListTheme
import com.example.composelist.view.screens.HomeScreen

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {

			ComposeListTheme {
				HomeScreen()
			}
		}
	}
}


//@Preview(name = "Light Mode")
//@Preview(
//	uiMode = Configuration.UI_MODE_NIGHT_YES,
//	showBackground = true,
//	name = "Dark Mode"
//)
//@Composable
//fun PreviewTaskList() {
//	ComposeListTheme() {
//		TaskList(tasks = MockTasks.tasks)
//	}
//}

