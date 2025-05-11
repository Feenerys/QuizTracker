package com.fit2081.quiztracker.data.quizAttempts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizAttemptDao {
    // Inserts a new [QuizAttempt] into the databse
    @Insert
    suspend fun insert(quizAttempt: QuizAttempt)

    // Retrieves all [QuizAttempt]s from the database as a Flow of lists
    @Query("SELECT * FROM quiz_attempts")
    fun getAllQuizAttempt(): Flow<List<QuizAttempt>>

    // Retrieves all [QuizAttempt]s for a specific student ID
    @Query("SELECT * FROM quiz_attempts WHERE studentId = :studentID")
    fun getQuizAttemptsByStudentId(studentId: String): Flow<List<QuizAttempt>>
}