package com.dgomez.developer.architecture.components.qa_client.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dgomez.developer.architecture.components.qa_client.domain.model.Question
import com.dgomez.developer.architecture.components.qa_client.domain.executor.PostExecutionThread
import com.dgomez.developer.architecture.components.qa_client.domain.executor.ThreadExecutor
import com.dgomez.developer.architecture.components.qa_client.domain.repository.QuestionsRepository

class GetQuestionsUseCase(
    private val questionsRepository: QuestionsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread):
    BaseBackgroundLiveDataInteractor<Unit, PagedList<Question>>(threadExecutor, postExecutionThread) {

    override fun run(inputParams: Unit): LiveData<PagedList<Question>> {
        return questionsRepository.getQuestionsFromServer()
    }
}