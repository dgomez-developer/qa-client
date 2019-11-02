package com.dgomez.developer.architecture.components.qa_client.domain.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dgomez.developer.architecture.components.qa_client.domain.executor.PostExecutionThread
import com.dgomez.developer.architecture.components.qa_client.domain.executor.ThreadExecutor
import com.dgomez.developer.architecture.components.qa_client.domain.model.Either

abstract class BaseBackgroundLiveDataInteractor<I, O>(
    private val backgroundThread: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    internal abstract fun run(inputParams: I): LiveData<O>

    operator fun invoke(inputParams: I): LiveData<Either<O, Throwable>> = buildLiveData(inputParams)

    private fun buildLiveData(inputParams: I): LiveData<Either<O, Throwable>> =
        MediatorLiveData<Either<O, Throwable>>().also {
            backgroundThread.execute(Runnable {
                val result = execute(inputParams)
                postExecutionThread.post(Runnable {
                    when (result) {
                        is Either.Success -> it.addSource(result.value) { t -> it.postValue(Either.Success(t)) }
                        is Either.Failure -> it.postValue(Either.Failure(result.error))
                    }

                })
            })
        }

    private fun execute(inputParams: I): Either<LiveData<O>, Throwable> = try {
        Either.Success(run(inputParams))
    } catch (throwable: Throwable) {
        Either.Failure(throwable)
    }
}