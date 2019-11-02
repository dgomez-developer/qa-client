package com.dgomez.developer.architecture.components.qa_client.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
class QuestionEntity (
    @PrimaryKey
    val id: String,
    val question: String,
    val contact: String?
)