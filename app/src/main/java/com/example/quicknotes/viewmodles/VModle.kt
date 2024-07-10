package com.example.quicknotes.viewmodles

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknotes.Dao.NotesDAo
import com.example.quicknotes.entities.Note
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VModle @Inject constructor(@ApplicationContext public  val context: Context,private val notesDAo: NotesDAo):ViewModel() {



    fun getALl():LiveData<List<Note>>{
        val lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(context)
        Log.w("Message",lastSignedInAccount?.id!!)
        return notesDAo.findByID(lastSignedInAccount?.id!!)
    }
    //112587337527134335700

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