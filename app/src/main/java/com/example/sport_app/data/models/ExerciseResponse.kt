package com.example.sport_app.data.models

data class ExerciseResponse(
    val results: List<Exercise>
)

data class Exercise(
    val id: Int,
    val category: Category,
    val translations: List<Translation>
)

data class Category(
    val id: Int,
    val name: String
)

data class Translation(
    val name: String?,
    val description: String?,
    val language: Int
)
