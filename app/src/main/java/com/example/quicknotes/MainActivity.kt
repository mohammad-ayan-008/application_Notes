package com.example.quicknotes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quicknotes.RecyclerAdapter.NotesListAdapter
import com.example.quicknotes.databinding.ActivityMainBinding
import com.example.quicknotes.entities.Note
import com.example.quicknotes.viewmodles.VModle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vModle:VModle  by viewModels()
    private var list = mutableListOf<Note>()
    public lateinit var notesListAdapter: NotesListAdapter
    private lateinit var  activityMainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesListAdapter = NotesListAdapter(vModle,list)
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
        activityMainBinding.addtask.setOnClickListener({
            startActivity(Intent(this,MainActivity2::class.java))
        })
    }
}