package com.fit2081.quiztracker.data.quizAttempts

import androidx.room.Entity
import androidx.room.PrimaryKey

// Represents a quiz attempt made by a student
@Entity(tableName = "quiz_attempts")
data class QuizAttempt(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentId: String,
    val quizId: String,
    val quizDate: String,
    val finalMark: Double
)