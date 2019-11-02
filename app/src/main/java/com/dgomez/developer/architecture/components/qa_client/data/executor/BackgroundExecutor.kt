package com.dgomez.developer.architecture.components.qa_client.data.executor

import com.dgomez.developer.architecture.components.qa_client.domain.executor.ThreadExecutor
import java.util.concurrent.*

class BackgroundExecutor: ThreadExecutor {

    companion object {
        private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
        private const val INITIAL_POOL_SIZE = 3
        private const val MAX_POOL_SIZE = 3
        private const val KEEP_ALIVE_TIME = 10L
    }

    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        val blockingQueue = LinkedBlockingQueue<Runnable>()
        threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, blockingQueue)
    }

    override fun execute(runnable: Runnable) {
        runnable.run {
            threadPoolExecutor.execute(this)
        }
    }

    override fun getThreadExecutor(): ExecutorService {
        return threadPoolExecutor
    }
}