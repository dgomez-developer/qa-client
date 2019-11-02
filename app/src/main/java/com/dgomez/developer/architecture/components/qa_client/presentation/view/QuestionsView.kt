package com.dgomez.developer.architecture.components.qa_client.presentation.view

import androidx.annotation.StringRes
import com.dgomez.developer.architecture.components.qa_client.presentation.model.QuestionViewItem

interface QuestionsView {

    fun showQuestions(questions: List<QuestionViewItem>)

    fun showError(@StringRes messageId: Int)
}