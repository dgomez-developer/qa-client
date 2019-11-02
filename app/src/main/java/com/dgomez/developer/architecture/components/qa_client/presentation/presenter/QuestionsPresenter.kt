package com.dgomez.developer.architecture.components.qa_client.presentation.presenter

import com.dgomez.developer.architecture.components.qa_client.presentation.view.QuestionsView

interface QuestionsPresenter {

    fun init()

    fun setView(view: QuestionsView?)
}