package com.sk.jettrivia.repository

import com.sk.jettrivia.network.QuestionApi
import com.sk.jettrivia.util.handleApi
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(private val api: QuestionApi) :
    QuestionRepository {

    override suspend fun getAllQuestions() = handleApi { api.getAllQuestions() }
}