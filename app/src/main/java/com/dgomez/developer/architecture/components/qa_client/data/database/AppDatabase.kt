package com.dgomez.developer.architecture.components.qa_client.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dgomez.developer.architecture.components.qa_client.data.model.QuestionEntity

@Database(entities = arrayOf(QuestionEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun questionsDao(): QuestionsDao

}