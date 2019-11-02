package com.dgomez.developer.architecture.components.qa_client.domain.interactor

import androidx.lifecycle.LiveData
import com.dgomez.developer.architecture.components.qa_client.domain.Question
import com.dgomez.developer.architecture.components.qa_client.domain.executor.PostExecutionThread
import com.dgomez.developer.architecture.components.qa_client.domain.executor.ThreadExecutor
import com.dgomez.developer.architecture.components.qa_client.domain.repository.QuestionsRepository

class GetQuestionsUseCase(
    private val questionsRepository: QuestionsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread):
    BaseBackgroundLiveDataInteractor<Unit, List<Question>>(threadExecutor, postExecutionThread) {

    override fun run(inputParams: Unit): LiveData<List<Question>> {
        return questionsRepository.getQuestions()
    }
}