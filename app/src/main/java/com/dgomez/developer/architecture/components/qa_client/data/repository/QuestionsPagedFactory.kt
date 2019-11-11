package com.dgomez.developer.architecture.components.qa_client.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dgomez.developer.architecture.components.qa_client.data.api.QuestionsApi
import com.dgomez.developer.architecture.components.qa_client.data.model.QuestionsRequestParams
import com.dgomez.developer.architecture.components.qa_client.data.repository.datasource.QuestionsPagedDataSource
import com.dgomez.developer.architecture.components.qa_client.domain.model.Question
import com.dgomez.developer.architecture.components.qa_client.domain.executor.ThreadExecutor

class QuestionsPagedFactory(

    private val api: QuestionsApi,

    val threadExecutor: ThreadExecutor) : DataSource.Factory<Int, Question>() {

    val errorLD: LiveData<Throwable>
        get() = mutableErrorLD

    val questionsLD: LiveData<List<Question>>
        get() = mutableQuestionsLD

    private val mutableErrorLD by lazy { MutableLiveData<Throwable>() }
    private val mutableQuestionsLD by lazy { MutableLiveData<List<Question>>() }

    override fun create(): DataSource<Int, Question> {
        return QuestionsPagedDataSource(api, QuestionsRequestParams(), mutableErrorLD, mutableQuestionsLD)
    }
}