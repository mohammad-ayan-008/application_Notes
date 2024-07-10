package com.example.quicknotes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private val vModle:VModle  by viewModels()
    @Inject
    public lateinit var list_fragment:List_data

    @Inject
    public lateinit var loginFragment: Login_Fragment

    private lateinit var  activityMainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(applicationContext,googleSignInOptions)


        enableEdgeToEdge()
       
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(activityMainBinding.tb)
        setContentView(activityMainBinding.root)

        val supportFragmentManager = supportFragmentManager
        supportFragmentManager.beginTransaction().replace(R.id.frames,loginFragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = MenuInflater(applicationContext)
        inflater.inflate(R.menu.menu_main,menu)
        return true
    }

    @SuppressLint("CommitTransaction")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.SignOut ->{
                googleSignInClient.signOut().addOnCompleteListener {
                    if (it.isSuccessful){
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frames,Login_Fragment())
                            .commit()
                    }
                }
            }
        }
        return false
    }
}