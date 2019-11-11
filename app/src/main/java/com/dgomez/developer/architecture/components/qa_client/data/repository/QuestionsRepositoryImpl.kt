package com.dgomez.developer.architecture.components.qa_client.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dgomez.developer.architecture.components.qa_client.data.repository.datasource.QuestionsLocalDataSource
import com.dgomez.developer.architecture.components.qa_client.domain.model.Question
import com.dgomez.developer.architecture.components.qa_client.domain.repository.QuestionsRepository

class QuestionsRepositoryImpl(
    private val questionsLocalDataSource: QuestionsLocalDataSource,
    private val questionsNetworkDataSource: QuestionsPagedFactory
) : QuestionsRepository {

    override fun getQuestions(): LiveData<List<Question>> {
        return questionsLocalDataSource.getAllQuestions()
    }

    override fun getQuestionsFromServer(): LiveData<PagedList<Question>> {

        val config: PagedList.Config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .setEnablePlaceholders(false)
            .setPrefetchDistance(6)
            .build()

        return LivePagedListBuilder(questionsNetworkDataSource, config)
            .setFetchExecutor(questionsNetworkDataSource.threadExecutor.getThreadExecutor())
            .build()
    }
}