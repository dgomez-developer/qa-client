package com.dgomez.developer.architecture.components.qa_client.domain.executor

interface PostExecutionThread {

    fun post(runnable: Runnable)
}