package com.dgomez.developer.architecture.components.qa_client.domain.interactor

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
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

    operator fun invoke(inputParams: I): LiveData<Either<O, Throwable>> = buildLiveData(inputParams)

    private fun buildLiveData(inputParams: I): LiveData<Either<O, Throwable>> = liveData<Either<O, Throwable>>(backgroundDispatcher) {
        val result = execute(inputParams)
        when(result){
            is Either.Success -> emitSource(Transformations.map(result.value, Function { Either.Success(it) }))
            is Either.Failure -> emit(Either.Failure(result.error))
        }
}

    private suspend fun execute(inputParams: I): Either<LiveData<O>, Throwable> = try {
        Either.Success(run(inputParams))
    } catch (throwable: Throwable) {
        Either.Failure(throwable)
    }

}