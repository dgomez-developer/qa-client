package com.dgomez.developer.architecture.components.qa_client.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgomez.developer.architecture.components.qa_client.R
import com.dgomez.developer.architecture.components.qa_client.presentation.model.QuestionViewItem
import com.dgomez.developer.architecture.components.qa_client.presentation.presenter.QuestionsPresenter
import com.dgomez.developer.architecture.components.qa_client.presentation.view.adapter.QuestionsListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), QuestionsView {


    private val presenter by inject<QuestionsPresenter>()

    private var adapter = QuestionsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list_of_questions.layoutManager = LinearLayoutManager(this)
        list_of_questions.adapter = adapter
        presenter.setView(this)
        presenter.init()
    }


    override fun showQuestions(questions: List<QuestionViewItem>) {
        adapter.questionsList = questions
    }

    override fun showError(messageId: Int) {
        Snackbar.make(list_of_questions_container, messageId, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.setView(null)
        super.onDestroy()

    }
}
