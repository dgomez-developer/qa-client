package com.dgomez.developer.architecture.components.qa_client.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgomez.developer.architecture.components.qa_client.R
import com.dgomez.developer.architecture.components.qa_client.presentation.view.adapter.QuestionsListAdapter
import com.dgomez.developer.architecture.components.qa_client.presentation.viewmodel.QuestionsListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<QuestionsListViewModel>()

    private var adapter = QuestionsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list_of_questions.layoutManager = LinearLayoutManager(this)
        list_of_questions.adapter = adapter

        viewModel.showQuestions().observe(this, Observer {
            adapter.questionsList = it
        })

        viewModel.showMessage().observe(this, Observer {
            Snackbar.make(list_of_questions_container, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.init()
    }


}
