package com.fit2081.quiztracker.data.quizAttempts

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// Viewmodel class for managing quiz attempts data
class QuizAttemptViewModel(context: Context) : ViewModel(){
    private val quizAttemptRepository: QuizAttemptsRepository =
        QuizAttemptsRepository(context)

    // Flow of all quiz attempts
    val allAttempts: Flow<List<QuizAttempt>> = quizAttemptRepository.allAttempts

    // Inserts a new quiz attempt into the database
    fun insertQuizAttempt(quizAttempt: QuizAttempt) {
        viewModelScope.launch { quizAttemptRepository.insert(quizAttempt) }
    }

    // Retrieves quiz attempts for a specific student ID
    fun getQuizAttemptByStudentId(studentId: String):
            Flow<List<QuizAttempt>> = quizAttemptRepository.getQuizAttemptByStudentId(studentId)

    class QuizAttemptViewModelFactory(context: Context): ViewModelProvider.Factory {
        private val context = context.applicationContext
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            QuizAttemptViewModel(context) as T
    }
}