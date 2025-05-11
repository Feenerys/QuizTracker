package com.fit2081.quiztracker.data.students

import android.content.Context
import com.fit2081.quiztracker.data.CollegeDatabase
import com.fit2081.quiztracker.data.quizAttempts.StudentsWithAverageMark
import kotlinx.coroutines.flow.Flow

class StudentsRepository(context: Context) {
    // Get the studentDao instance from the database
    private val studentsDao = CollegeDatabase.getDatabase(context).studentDao()

    // Inserts a new student into the database
    suspend fun insertStudent(student: Student) {
        studentsDao.insert(student)
    }

    // Retrieves a student from the database by their ID
    suspend fun getStudentById(studentId: String): Student {
        return studentsDao.getStudentById(studentId)
    }

    // Retrieves all students from the database as a Flow
    fun getAllStudents(): Flow<List<Student>> = studentsDao.getAll()

    // Retrieves students along with their average marks as a Flow
    fun getStudentsWithAverageMarks(): Flow<List<StudentsWithAverageMark>> =
        studentsDao.getStudentsWithAverageMarks()
}