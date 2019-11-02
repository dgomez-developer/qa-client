package com.dgomez.developer.architecture.components.qa_client.presentation.executor

import android.os.Handler
import android.os.Looper
import com.dgomez.developer.architecture.components.qa_client.domain.executor.PostExecutionThread

class UiThread : PostExecutionThread {

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun post(runnable: Runnable) {
        handler.post(runnable)
    }
}