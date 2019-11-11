package com.dgomez.developer.architecture.components.qa_client.data.repository.datasource

import androidx.lifecycle.Transformations
import com.dgomez.developer.architecture.components.qa_client.data.database.AppDatabase
import com.dgomez.developer.architecture.components.qa_client.data.model.QuestionEntity
import com.dgomez.developer.architecture.components.qa_client.domain.model.Question

class QuestionsLocalDataSource(val db: AppDatabase) {

//    fun getAllQuestions(): LiveData<List<Question>> = MediatorLiveData<List<Question>>().apply {
//        val dbLD = db.questionsDao().getAll()
//        this.addSource(dbLD) {
//            val questions = it.map { dbquestion -> Question(dbquestion.id, dbquestion.question, dbquestion.contact) }
//            this.postValue(questions)
//        }
//    }

    fun getAllQuestions() = Transformations.map(db.questionsDao().getAll()) {
            input -> input.map{ dbquestion ->
        Question(
            dbquestion.id,
            dbquestion.question,
            dbquestion.contact
        )
    } }

    fun updateQuestions(questions: List<Question>) {
        db.questionsDao().insertAll(questions.map { QuestionEntity(it.id, it.question, it.contact) })
    }

}