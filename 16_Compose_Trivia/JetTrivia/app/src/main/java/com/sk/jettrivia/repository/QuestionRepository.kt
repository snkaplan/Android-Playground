package com.sk.jettrivia.repository

import com.sk.jettrivia.model.TriviaQuestions
import com.sk.jettrivia.util.ApiResult

interface QuestionRepository {
    suspend fun getAllQuestions(): ApiResult<TriviaQuestions>
}