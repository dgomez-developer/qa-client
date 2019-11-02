package com.dgomez.developer.architecture.components.qa_client.domain.interactor

import com.dgomez.developer.architecture.components.qa_client.domain.executor.PostExecutionThread
import com.dgomez.developer.architecture.components.qa_client.domain.executor.ThreadExecutor

abstract class BaseBackgroundInteractor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) : Runnable {


    abstract override fun run()

    fun execute() {
        threadExecutor.execute(this)
    }

    fun post(runnable: Runnable) {
        postExecutionThread.post(runnable)
    }
}