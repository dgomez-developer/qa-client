package com.dgomez.developer.architecture.components.qa_client.data.repository.datasource

import com.dgomez.developer.architecture.components.qa_client.data.api.QuestionsApi
import com.dgomez.developer.architecture.components.qa_client.domain.Question

class QuestionsNetworkDataSource(private val api: QuestionsApi) {

    fun getQuestions(): List<Question> {
        val questionsCall = api.getQuestions()
        val response = questionsCall.execute()
        return if(response.isSuccessful){
            response.body() ?: emptyList()
        } else {
            throw Throwable()
        }
    }

}