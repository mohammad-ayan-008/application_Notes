package com.example.quicknotes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quicknotes.Fragments.List_data
import com.example.quicknotes.Fragments.Login_Fragment
import com.example.quicknotes.RecyclerAdapter.NotesListAdapter
import com.example.quicknotes.databinding.ActivityMainBinding
import com.example.quicknotes.entities.Note
import com.example.quicknotes.viewmodles.VModle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vModle:VModle  by viewModels()
    @Inject
    public lateinit var list_fragment:List_data

    @Inject
    public lateinit var loginFragment: Login_Fragment

    private lateinit var  activityMainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
       
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(activityMainBinding.tb)
        setContentView(activityMainBinding.root)

        val supportFragmentManager = supportFragmentManager
        supportFragmentManager.beginTransaction().replace(R.id.frames,loginFragment).commit()
    }
}