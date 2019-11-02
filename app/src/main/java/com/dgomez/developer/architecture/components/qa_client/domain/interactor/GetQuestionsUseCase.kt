package com.dgomez.developer.architecture.components.qa_client.domain.interactor

import com.dgomez.developer.architecture.components.qa_client.data.model.Callback
import com.dgomez.developer.architecture.components.qa_client.domain.Question
import com.dgomez.developer.architecture.components.qa_client.domain.executor.PostExecutionThread
import com.dgomez.developer.architecture.components.qa_client.domain.executor.ThreadExecutor
import com.dgomez.developer.architecture.components.qa_client.domain.repository.QuestionsRepository

class GetQuestionsUseCase(
    private val questionsRepository: QuestionsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : BaseBackgroundInteractor(threadExecutor, postExecutionThread) {

    lateinit var callback: Callback<List<Question>, Throwable>

    override fun run() {

        questionsRepository.getQuestions(object : Callback<List<Question>, Throwable> {
            override fun onSuccess(result: List<Question>) {
                post(Runnable {
                    callback.onSuccess(result)
                })
            }

            override fun onError(error: Throwable) {
                post(Runnable {
                    callback.onError(error)
                })
            }
        })
    }

    fun invoke(callback: Callback<List<Question>, Throwable>) {
        this.callback = callback
        execute()
    }
}