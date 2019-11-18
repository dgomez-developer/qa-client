package com.dgomez.developer.architecture.components.qa_client.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dgomez.developer.architecture.components.qa_client.R
import com.dgomez.developer.architecture.components.qa_client.presentation.model.QuestionViewItem
import kotlinx.android.synthetic.main.question_item.view.*
import kotlin.properties.Delegates

class QuestionsListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var questionsList: List<QuestionViewItem> by Delegates.observable(
        emptyList(),
        onChange = { _, _, _ -> notifyDataSetChanged() })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(questionsList[position])
    }

    private class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(question: QuestionViewItem) {
            itemView.question_title.text = question.question
            itemView.question_contact.text = question.contact
        }

    }
}