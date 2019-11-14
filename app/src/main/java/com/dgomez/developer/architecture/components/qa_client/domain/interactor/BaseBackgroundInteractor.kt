package com.dgomez.developer.architecture.components.qa_client.domain.interactor

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asFlow
import com.dgomez.developer.architecture.components.qa_client.domain.model.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @author Madrid Tech Lab on 2019-11-13.
 */
abstract class BaseBackgroundInteractor<I, O>(
    private val backgroundDispatcher: CoroutineDispatcher
) {

    abstract suspend fun run(inputParams: I): LiveData<O>

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    operator fun invoke(inputParams: I): Flow<Either<O, Throwable>> = buildFlow(inputParams)

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    private fun buildFlow(inputParams: I): Flow<Either<O, Throwable>> = flow<Either<O, Throwable>> {
        val result = execute(inputParams)
        when (result) {
            is Either.Success -> emitAll(
                Transformations.map(
                    result.value,
                    Function<O, Either<O, Throwable>> { Either.Success(it) }).asFlow()
            )
            is Either.Failure -> emit(Either.Failure(result.error))
        }

    }.flowOn(backgroundDispatcher)

    private suspend fun execute(inputParams: I): Either<LiveData<O>, Throwable> = try {
        Either.Success(run(inputParams))
    } catch (throwable: Throwable) {
        Either.Failure(throwable)
    }

}