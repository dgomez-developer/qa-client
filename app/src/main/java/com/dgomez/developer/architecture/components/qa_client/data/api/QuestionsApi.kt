package com.dgomez.developer.architecture.components.qa_client.data.api

import com.dgomez.developer.architecture.components.qa_client.domain.model.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionsApi {

    @GET("questions")
    fun getQuestions(): Call<List<Question>>

    @GET("questions")
    fun getQuestions(@Query("page") page: Int?, @Query("pageSize") pageSize: Int?): Call<List<Question>>

}