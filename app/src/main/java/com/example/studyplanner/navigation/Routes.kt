package com.example.studyplanner.navigation

import kotlinx.serialization.Serializable

@Serializable
object PlannerHome

@Serializable
data class TaskDetail(val title: String)