package com.dgomez.developer.architecture.components.qa_client.domain.executor

import java.util.concurrent.ExecutorService

interface ThreadExecutor {

    fun execute(runnable: Runnable)

    fun getThreadExecutor(): ExecutorService
}