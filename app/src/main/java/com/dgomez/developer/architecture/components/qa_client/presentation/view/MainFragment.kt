package com.dgomez.developer.architecture.components.qa_client.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgomez.developer.architecture.components.qa_client.R
import com.dgomez.developer.architecture.components.qa_client.presentation.view.adapter.QuestionsListAdapter
import com.dgomez.developer.architecture.components.qa_client.presentation.viewmodel.QuestionsListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_questions_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Madrid Tech Lab on 2019-11-14.
 */
class MainFragment: Fragment() {

    private val viewModel by viewModel<QuestionsListViewModel>()

    private var adapter = QuestionsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return  inflater.inflate(R.layout.fragment_questions_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list_of_questions.layoutManager = LinearLayoutManager(requireContext())
        list_of_questions.adapter = adapter
        swiperefresh.setOnRefreshListener {
            viewModel.init()
        }
        create_question.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToCreateQuestionFragment()
            view?.findNavController()?.navigate(action)
        }
        viewModel.showQuestions().observe(viewLifecycleOwner, Observer {
            adapter.questionsList = it
            swiperefresh.isRefreshing = false
        })

        viewModel.showMessage().observe(viewLifecycleOwner, Observer {
            Snackbar.make(list_of_questions_container, it, Snackbar.LENGTH_LONG).show()
            swiperefresh.isRefreshing = false
        })
        viewModel.init()
    }

}