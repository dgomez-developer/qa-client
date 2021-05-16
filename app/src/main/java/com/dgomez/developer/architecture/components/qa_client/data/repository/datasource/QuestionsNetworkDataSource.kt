package com.dgomez.developer.architecture.components.qa_client.data.repository.datasource

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.dgomez.developer.architecture.components.qa_client.QuestionsQuery
import com.dgomez.developer.architecture.components.qa_client.domain.Question

class QuestionsNetworkDataSource(private val apollo: ApolloClient) {

    suspend fun getQuestions(): List<Question> {

        val questionsCall = apollo.query(QuestionsQuery())
        val response = questionsCall.await()
        return if(response.hasErrors() || response.data == null){
            throw Throwable()
        } else {
            response.data?.getRecentQa?.map {
                Question(it?.id ?: "", it?.question ?: "", it?.contact)
            } ?: emptyList()
        }
    }

}