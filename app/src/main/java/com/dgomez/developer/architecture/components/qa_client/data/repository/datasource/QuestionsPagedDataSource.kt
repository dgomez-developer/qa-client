package com.dgomez.developer.architecture.components.qa_client.data.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dgomez.developer.architecture.components.qa_client.data.api.QuestionsApi
import com.dgomez.developer.architecture.components.qa_client.data.model.QuestionsRequestParams
import com.dgomez.developer.architecture.components.qa_client.domain.model.Question

class QuestionsPagedDataSource(
    private val api: QuestionsApi,
    private val requestParams: QuestionsRequestParams,
    private val errorLD: MutableLiveData<Throwable>,
    private val questionsLD: MutableLiveData<List<Question>>
) :
    PageKeyedDataSource<Int, Question>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Question>) {
        val questionsCall = api.getQuestions(requestParams.page, requestParams.pageSize)
        val response = questionsCall.execute()
        return if (response.isSuccessful) {
            questionsLD.postValue(response.body() ?: emptyList())
            callback.onResult(response.body()?.toMutableList() ?: mutableListOf(), null, requestParams.page + 1)
        } else {
            errorLD.postValue(Throwable())
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Question>) {
        val questionsCall = api.getQuestions(params.key, requestParams.pageSize)
        val response = questionsCall.execute()
        return if (response.isSuccessful) {
            questionsLD.postValue(response.body() ?: emptyList())
            if (params.requestedLoadSize >= (params.key + 1)) {
                callback.onResult(response.body()?.toMutableList() ?: mutableListOf(), params.key + 1)
            } else {
                callback.onResult(response.body()?.toMutableList() ?: mutableListOf(), null)
            }
        } else {
            errorLD.postValue(Throwable())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Question>) {
        // TODO("not implemented")
    }
}