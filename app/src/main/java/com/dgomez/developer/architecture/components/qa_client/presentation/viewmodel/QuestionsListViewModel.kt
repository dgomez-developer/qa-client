package com.dgomez.developer.architecture.components.qa_client.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dgomez.developer.architecture.components.qa_client.R
import com.dgomez.developer.architecture.components.qa_client.domain.model.Question
import com.dgomez.developer.architecture.components.qa_client.domain.interactor.GetQuestionsUseCase
import com.dgomez.developer.architecture.components.qa_client.domain.model.Either

class QuestionsListViewModel(private val getQuestionsUseCase: GetQuestionsUseCase) : ViewModel() {

    private val questionsLD by lazy { MediatorLiveData<PagedList<Question>>() }
    private val messageLD by lazy { MutableLiveData<Int>() }
    private val loaderLD by lazy { MutableLiveData<Boolean>() }

    fun showQuestions() = questionsLD
    fun showMessage() = messageLD

    fun init() {
        loaderLD.value = true
        val listOfQuestionsLD = getQuestionsUseCase.invoke(Unit)
        questionsLD.removeSource(listOfQuestionsLD)
        questionsLD.addSource(listOfQuestionsLD) {
            when (it) {
                is Either.Success -> questionsLD.value = it.value
                is Either.Failure -> messageLD.value = R.string.error_getting_questions
            }
            loaderLD.value = false
        }
    }

}