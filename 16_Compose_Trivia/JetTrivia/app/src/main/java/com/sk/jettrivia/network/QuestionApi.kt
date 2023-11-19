package com.sk.jettrivia.network

import com.sk.jettrivia.model.TriviaQuestions
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {
    @GET("world.json")
    suspend fun getAllQuestions(): Response<TriviaQuestions>
}