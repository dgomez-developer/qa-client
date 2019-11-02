package com.dgomez.developer.architecture.components.qa_client.data.repository.datasource

import com.dgomez.developer.architecture.components.qa_client.data.database.AppDatabase
import com.dgomez.developer.architecture.components.qa_client.data.model.QuestionEntity
import com.dgomez.developer.architecture.components.qa_client.domain.Question

class QuestionsLocalDataSource(val db: AppDatabase) {

    fun getAllQuestions(): List<Question> {
        return db.questionsDao().getAll()
            .map { dbquestion -> Question(dbquestion.id, dbquestion.question, dbquestion.contact) }
    }

    fun updateQuestions(questions: List<Question>) {
        db.questionsDao().insertAll(questions.map { QuestionEntity(it.id, it.question, it.contact) })
    }

}