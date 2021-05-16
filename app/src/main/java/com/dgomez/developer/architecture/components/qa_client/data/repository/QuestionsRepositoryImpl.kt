package com.dgomez.developer.architecture.components.qa_client.data.repository

import androidx.lifecycle.LiveData
import com.dgomez.developer.architecture.components.qa_client.data.repository.datasource.QuestionsLocalDataSource
import com.dgomez.developer.architecture.components.qa_client.data.repository.datasource.QuestionsNetworkDataSource
import com.dgomez.developer.architecture.components.qa_client.domain.Question
import com.dgomez.developer.architecture.components.qa_client.domain.repository.QuestionsRepository

class QuestionsRepositoryImpl(
    private val questionsLocalDataSource: QuestionsLocalDataSource,
    private val questionsNetworkDataSource: QuestionsNetworkDataSource): QuestionsRepository {

    override suspend fun getQuestions(): LiveData<List<Question>> {
        getQuestionsFromServer()
        return questionsLocalDataSource.getAllQuestions()
    }

    private suspend fun getQuestionsFromServer() {
        val questions = questionsNetworkDataSource.getQuestions()
        questionsLocalDataSource.updateQuestions(questions)
    }
}