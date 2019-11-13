package com.dgomez.developer.architecture.components.qa_client.domain.repository

import androidx.lifecycle.LiveData
import com.dgomez.developer.architecture.components.qa_client.domain.Question

interface QuestionsRepository {

    suspend fun getQuestions(): LiveData<List<Question>>

}