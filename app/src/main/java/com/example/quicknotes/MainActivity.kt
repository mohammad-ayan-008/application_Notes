package com.example.quicknotes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.quicknotes.Databse.Database_room
import com.example.quicknotes.databinding.ActivityMainBinding
import com.example.quicknotes.entities.Note
import com.example.quicknotes.viewmodles.VModle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vModle:VModle  by viewModels()
    private var list = mutableListOf<Note>()
    public lateinit var notesListAdapter:NotesListAdapter
    private lateinit var  activityMainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesListAdapter = NotesListAdapter(list)
        enableEdgeToEdge()
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(activityMainBinding.tb)
        setContentView(activityMainBinding.root)
        val recycler = activityMainBinding.rview
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = notesListAdapter
        notesListAdapter.notifyDataSetChanged()
        vModle.allnotes.observe(this@MainActivity,{
            if (!list.isEmpty()) {
                list.clear()
            }
            list.addAll(it)
            notesListAdapter.notifyDataSetChanged()
        })
  //  vModle.add(Note(note = "make a rest Api for user page", priority = "k", date = "03", month = "Jul", title = "work", stauts = true, day ="Wed" ))
    }
}