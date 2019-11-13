package com.dgomez.developer.architecture.components.qa_client.domain.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dgomez.developer.architecture.components.qa_client.domain.model.Either
import kotlinx.coroutines.CoroutineDispatcher

/**
 * @author Madrid Tech Lab on 2019-11-13.
 */
abstract class BaseBackgroundInteractor<I, O>(
    private val backgroundDispatcher: CoroutineDispatcher
) {

    abstract suspend fun run(inputParams: I): LiveData<O>

    operator fun invoke(inputParams: I): LiveData<Either<O, Throwable>> = liveData(backgroundDispatcher) {
        val result = execute(inputParams)
        when(result){
            is Either.Success -> result.value.value?.let { emit(Either.Success(it)) }
            is Either.Failure -> emit(Either.Failure(result.error))
        }
    }

    private suspend fun execute(inputParams: I): Either<LiveData<O>, Throwable> = try {
        Either.Success(run(inputParams))
    } catch (throwable: Throwable) {
        Either.Failure(throwable)
    }

}