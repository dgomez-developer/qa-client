package com.dgomez.developer.architecture.components.qa_client.domain.repository

import com.dgomez.developer.architecture.components.qa_client.data.model.Callback
import com.dgomez.developer.architecture.components.qa_client.domain.Question

interface QuestionsRepository {

    fun getQuestions(callback: Callback<List<Question>, Throwable>)

}