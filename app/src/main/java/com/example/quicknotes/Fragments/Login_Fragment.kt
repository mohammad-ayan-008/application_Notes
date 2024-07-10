package com.example.quicknotes.Fragments

import android.R.attr.data
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.quicknotes.R
import com.example.quicknotes.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task




class Login_Fragment : Fragment() {

    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var layoutLoginBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
        val signInButton = layoutLoginBinding.signInButton
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        var con:Context=requireContext()
        googleSignInClient = GoogleSignIn.getClient(con,googleSignInOptions)

        val lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(con)
        if (lastSignedInAccount != null){
            parentFragmentManager.beginTransaction().
            replace(R.id.frames,List_data())
                .commit()
        }
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener {
           signIn()
        }
        // Inflate the layout for this fragment
        return layoutLoginBinding.root
    }

    private fun signIn() {
        val signInIntent: Intent = googleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent,100)
    //result.launch(signInIntent)
    }

    private var result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        handleSignInResult(task)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Toast.makeText(requireContext(),"Logined as"+account.displayName,Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().
            replace(R.id.frames,List_data())
                .commit()
        } catch (e: ApiException) {
            Log.e("ERROR",e.toString())

        }
    }

}