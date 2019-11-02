package com.dgomez.developer.architecture.components.qa_client.presentation.presenter

import android.app.Activity
import com.dgomez.developer.architecture.components.qa_client.R
import com.dgomez.developer.architecture.components.qa_client.data.model.Callback
import com.dgomez.developer.architecture.components.qa_client.domain.Question
import com.dgomez.developer.architecture.components.qa_client.domain.interactor.GetQuestionsUseCase
import com.dgomez.developer.architecture.components.qa_client.presentation.model.QuestionViewItem
import com.dgomez.developer.architecture.components.qa_client.presentation.view.QuestionsView

class QuestionsPresenterImpl(private val getQuestionsUseCase: GetQuestionsUseCase): QuestionsPresenter {

    override fun setView(view: QuestionsView?) {
        this.view = view
    }

    private var view: QuestionsView? = null

    override fun init() {

        getQuestionsUseCase.invoke(object : Callback<List<Question>, Throwable> {
            override fun onSuccess(result: List<Question>) {
                if((view as? Activity)?.isFinishing == false){
                    view?.showQuestions(result.map { question -> QuestionViewItem(question.id, question.question, question.contact) })
                }
            }

            override fun onError(error: Throwable) {
                if((view as? Activity)?.isFinishing == false){
                    view?.showError(R.string.error_getting_questions)
                }
            }

        })

    }
}