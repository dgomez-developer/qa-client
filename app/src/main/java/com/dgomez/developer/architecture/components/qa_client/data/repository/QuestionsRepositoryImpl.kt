package com.dgomez.developer.architecture.components.qa_client.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dgomez.developer.architecture.components.qa_client.data.repository.datasource.QuestionsLocalDataSource
import com.dgomez.developer.architecture.components.qa_client.data.repository.datasource.QuestionsNetworkDataSource
import com.dgomez.developer.architecture.components.qa_client.domain.Question
import com.dgomez.developer.architecture.components.qa_client.domain.repository.QuestionsRepository

class QuestionsRepositoryImpl(
    private val questionsLocalDataSource: QuestionsLocalDataSource,
    private val questionsNetworkDataSource: QuestionsNetworkDataSource): QuestionsRepository {

    override fun getQuestions(): LiveData<List<Question>> {
        return MutableLiveData<List<Question>>().apply { postValue(questionsNetworkDataSource.getQuestions()) }
    }

    private fun getQuestionsFromServer() {
        val questions = questionsNetworkDataSource.getQuestions()
        questionsLocalDataSource.updateQuestions(questions)
    }
}