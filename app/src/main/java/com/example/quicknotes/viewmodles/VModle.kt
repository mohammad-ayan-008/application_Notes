package com.example.quicknotes.viewmodles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknotes.Dao.NotesDAo
import com.example.quicknotes.entities.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VModle @Inject constructor(private val notesDAo: NotesDAo):ViewModel() {

    private lateinit var LiveData:LiveData<List<Note>>

    public val allnotes:LiveData<List<Note>>
        get() = LiveData

    init {
        viewModelScope.launch {
            LiveData=notesDAo.findAll()
        }
    }

   fun add(note: Note){
       viewModelScope.launch {
           notesDAo.save(note)
       }
   }
    fun delete(note: Note){
        viewModelScope.launch {
            notesDAo.delete(note)
        }
    }
    fun update(note: Note){
        viewModelScope.launch {
            notesDAo.update(note)
        }
    }
}