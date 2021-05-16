package com.dgomez.developer.architecture.components.qa_client.data.api

import com.dgomez.developer.architecture.components.qa_client.domain.Question
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface QuestionsApi {

    @GET("questions")
    suspend fun getQuestions(): Response<List<Question>>

}