package com.sk.jettrivia.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sk.jettrivia.model.QuestionItem
import com.sk.jettrivia.model.TriviaQuestions
import com.sk.jettrivia.repository.QuestionRepository
import com.sk.jettrivia.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionRepository) :
    ViewModel() {

    var uiState = MutableStateFlow<QuestionsUiState>(QuestionsUiState.Loading)
        private set
    var currentQuestionFlow = MutableStateFlow(CurrentQuestionData(null, -1))
        private set

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            when (val result = repository.getAllQuestions()) {
                is ApiResult.Error -> uiState.value = QuestionsUiState.Error
                is ApiResult.Loading -> uiState.value = QuestionsUiState.Loading
                is ApiResult.Success -> {
                    if (result.data != null) {
                        uiState.value = QuestionsUiState.Success(result.data)
                        getNextQuestion()
                    }
                }
            }
        }
    }

    fun getNextQuestion() {
        viewModelScope.launch {
            val state = uiState.value as? QuestionsUiState.Success
            val currentQuestionData = currentQuestionFlow.value
            if (state != null) {
                val nextIdx = currentQuestionData.index + 1
                currentQuestionFlow.value =
                    currentQuestionFlow.value.copy(
                        question = state.questions[nextIdx],
                        index = nextIdx
                    )
            }
        }
    }
}

sealed class QuestionsUiState {
    data class Success(val questions: TriviaQuestions) : QuestionsUiState()
    object Error : QuestionsUiState()
    object Loading : QuestionsUiState()
}

data class CurrentQuestionData(val question: QuestionItem?, val index: Int)
