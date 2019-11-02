package com.dgomez.developer.architecture.components.qa_client.data.model

interface Callback<T,E> {

    fun onSuccess(result: T)

    fun onError(error: E)
}