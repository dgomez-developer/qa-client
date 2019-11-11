package com.dgomez.developer.architecture.components.qa_client.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dgomez.developer.architecture.components.qa_client.R
import com.dgomez.developer.architecture.components.qa_client.domain.model.Question

class QuestionsPagedAdapter : PagedListAdapter<Question, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val question = getItem(position)
        if (question != null) {
            (holder as QuestionsViewHolder).bind(question)
        }
    }

    class QuestionsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(question: Question) {
            (itemView as AppCompatTextView).text = question.question
        }

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Question>() {
            override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean =
                oldItem == newItem

        }
    }

}