package com.example.studyplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.studyplanner.navigation.PlannerHome
import com.example.studyplanner.navigation.TaskDetail
import com.example.studyplanner.ui.theme.PlannerHomeScreen
import com.example.studyplanner.ui.theme.PlannerUiState
import com.example.studyplanner.ui.theme.TaskDetailRoute
import com.example.studyplanner.ui.theme.StudyPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyPlannerTheme {
                StudyPlannerApp()
            }
        }
    }
}

@Composable
fun StudyPlannerApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PlannerHome) {
        composable<PlannerHome> {
            PlannerHomeScreen(
                uiState = PlannerUiState(),
                onTaskClick = { taskTitle ->
                    navController.navigate(TaskDetail(title = taskTitle))
                }
            )
        }
        composable<TaskDetail> { backStackEntry ->
            val detail: TaskDetail = backStackEntry.toRoute()
            TaskDetailRoute(
                title = detail.title,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}