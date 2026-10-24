package com.example.studyplanner.ui.theme

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

data class PlannerUiState(
    val tasks: List<String> = listOf("Lab 5 report", "Database assignment", "UI design review")
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Study Planner", style = MaterialTheme.typography.headlineMedium)
        uiState.tasks.forEach { taskTitle ->
            Button(onClick = { onTaskClick(taskTitle) }) {
                Text(taskTitle)
            }
        }
    }
}

data class TaskDetailUiState(
    val title: String = ""
)

@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(uiState.title, style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onShareClick) { Text("Share") }
        OutlinedButton(onClick = onBackClick) { Text("Back") }
    }
}

@Composable
fun TaskDetailRoute(title: String, onBackClick: () -> Unit) {
    val context = LocalContext.current
    TaskDetailScreen(
        uiState = TaskDetailUiState(title = title),
        onShareClick = {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, "Today I'm studying: $title")
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(sendIntent, "Share via"))
        },
        onBackClick = onBackClick
    )
}