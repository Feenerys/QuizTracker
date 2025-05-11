package com.fit2081.quiztracker.data.students

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fit2081.quiztracker.data.quizAttempts.StudentsWithAverageMark
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    // Inserts a new student into the database
    @Insert
    suspend fun insert(student: Student)

    // Retrieves a student from the databased based on their ID
    @Query("SELECT * FROM students WHERE studentId = :studentId")
    suspend fun getStudentById(studentId: String): Student

    // Retrieves all students from the database
    @Query("SELECT * FROM students")
    fun getAll(): Flow<List<Student>>

    // Retrieves a list of students along with their average quiz marks
    @Query("SELECT students.studentId, students.studentName, AVG(quiz_attempts.finalMark) as " +
            "averageMark FROM students Inner Join quiz_attempts " +
            "ON students.studentId = quiz_attempts.studentId Group by students.studentId")
    fun getStudentsWithAverageMarks(): Flow<List<StudentsWithAverageMark>>
}