package com.example.quicknotes.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Insert
import com.example.quicknotes.Dao.NotesDAo
import com.example.quicknotes.Databse.Database_room
import com.example.quicknotes.entities.Note
import javax.inject.Inject

class NotesRepo @Inject constructor(private val notesDAO: NotesDAo) {

    suspend fun save(note: Note) {
        notesDAO.save(note)
    }

    suspend fun findAll(): LiveData<List<Note>> {
        var data = findAll()
        return data
    }

    suspend fun delete(note: Note) {
        notesDAO.delete(note)
    }

     fun findByPriority(priority: String): MutableLiveData<List<Note>> {
        return MutableLiveData<List<Note>>(notesDAO.findByPriority(priority))
    }
     fun findBuUserId(uid:String):LiveData<List<Note>>{
        return notesDAO.findByID(uid)
    }

    suspend fun update(note: Note) {
        notesDAO.update(note)
    }

}