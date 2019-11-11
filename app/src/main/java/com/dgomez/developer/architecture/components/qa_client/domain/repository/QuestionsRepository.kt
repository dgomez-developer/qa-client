package com.dgomez.developer.architecture.components.qa_client.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dgomez.developer.architecture.components.qa_client.domain.model.Question

interface QuestionsRepository {

    fun getQuestions(): LiveData<List<Question>>

    fun getQuestionsFromServer(): LiveData<PagedList<Question>>
}