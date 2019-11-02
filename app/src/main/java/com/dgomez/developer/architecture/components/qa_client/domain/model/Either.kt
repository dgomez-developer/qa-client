package com.dgomez.developer.architecture.components.qa_client.domain.model

sealed class Either<O, E> {
    data class Success<O, E>(val value: O) : Either<O,E>()
    data class Failure<O, E>(val error: E) : Either<O,E>()
}