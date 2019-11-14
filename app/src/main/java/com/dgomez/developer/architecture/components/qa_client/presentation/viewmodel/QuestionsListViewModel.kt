package com.dgomez.developer.architecture.components.qa_client.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgomez.developer.architecture.components.qa_client.R
import com.dgomez.developer.architecture.components.qa_client.domain.interactor.GetQuestionsUseCase
import com.dgomez.developer.architecture.components.qa_client.domain.model.Either
import com.dgomez.developer.architecture.components.qa_client.presentation.model.QuestionViewItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuestionsListViewModel(private val getQuestionsUseCase: GetQuestionsUseCase) : ViewModel() {

    private val questionsLD by lazy { MediatorLiveData<List<QuestionViewItem>>() }
    private val messageLD by lazy { MutableLiveData<Int>() }
    private val loaderLD by lazy { MutableLiveData<Boolean>() }

    fun showQuestions() = questionsLD
    fun showMessage() = messageLD

    fun init() {
        loaderLD.value = true
        viewModelScope.launch {
            val listOfQuestionsLD = getQuestionsUseCase.invoke(Unit)
            listOfQuestionsLD.collect {
                when (it) {
                    is Either.Success -> questionsLD.value =
                        it.value.map { question ->
                            QuestionViewItem(
                                question.id,
                                question.question,
                                question.contact
                            )
                        }
                    is Either.Failure -> messageLD.value = R.string.error_getting_questions
                }
                loaderLD.value = false
            }
        }
    }

}