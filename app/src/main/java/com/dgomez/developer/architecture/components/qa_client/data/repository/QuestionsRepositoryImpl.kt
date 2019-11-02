package com.dgomez.developer.architecture.components.qa_client.data.repository

import com.dgomez.developer.architecture.components.qa_client.data.model.Callback
import com.dgomez.developer.architecture.components.qa_client.data.repository.datasource.QuestionsLocalDataSource
import com.dgomez.developer.architecture.components.qa_client.data.repository.datasource.QuestionsNetworkDataSource
import com.dgomez.developer.architecture.components.qa_client.domain.Question
import com.dgomez.developer.architecture.components.qa_client.domain.repository.QuestionsRepository

class QuestionsRepositoryImpl(
    private val questionsLocalDataSource: QuestionsLocalDataSource,
    private val questionsNetworkDataSource: QuestionsNetworkDataSource
) : QuestionsRepository {

    override fun getQuestions(callback: Callback<List<Question>, Throwable>) {
        callback.onSuccess(questionsLocalDataSource.getAllQuestions())
        getQuestionsFromServer(object : Callback<List<Question>, Throwable> {

            override fun onError(error: Throwable) {
                callback.onError(error)
            }

            override fun onSuccess(result: List<Question>) {
                questionsLocalDataSource.updateQuestions(result)
                callback.onSuccess(questionsLocalDataSource.getAllQuestions())
            }

        })
    }

    private fun getQuestionsFromServer(callback: Callback<List<Question>, Throwable>) {
        try {
            val questions = questionsNetworkDataSource.getQuestions()
            callback.onSuccess(questions)
        } catch (throwable: Throwable) {
            callback.onError(throwable)
        }
    }
}