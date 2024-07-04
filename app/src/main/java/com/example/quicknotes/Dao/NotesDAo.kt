package com.example.quicknotes.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quicknotes.entities.Note


@Dao
interface NotesDAo {

    @Insert
    suspend fun save(note: Note)

    @Query("Select * from Notes")
     fun findAll():LiveData<List<Note>>

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from Notes where priorities = :priority")
    suspend fun findByPriority(priority:String):List<Note>

    @Update
    suspend fun update(note: Note)
}