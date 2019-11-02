package com.dgomez.developer.architecture.components.qa_client.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dgomez.developer.architecture.components.qa_client.data.model.QuestionEntity

@Dao
interface QuestionsDao {

    @Query("SELECT * from question")
    fun getAll(): LiveData<List<QuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(map: List<QuestionEntity>)
}