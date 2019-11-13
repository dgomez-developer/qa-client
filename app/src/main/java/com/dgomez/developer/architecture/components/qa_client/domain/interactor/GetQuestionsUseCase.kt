package com.dgomez.developer.architecture.components.qa_client.domain.interactor

import androidx.lifecycle.LiveData
import com.dgomez.developer.architecture.components.qa_client.domain.Question
import com.dgomez.developer.architecture.components.qa_client.domain.executor.PostExecutionThread
import com.dgomez.developer.architecture.components.qa_client.domain.executor.ThreadExecutor
import com.dgomez.developer.architecture.components.qa_client.domain.repository.QuestionsRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetQuestionsUseCase(
    private val questionsRepository: QuestionsRepository,
    backgroundThread: CoroutineDispatcher):
    BaseBackgroundInteractor<Unit, List<Question>>(backgroundThread) {

    override suspend fun run(inputParams: Unit): LiveData<List<Question>> {
        return questionsRepository.getQuestions()
    }
}