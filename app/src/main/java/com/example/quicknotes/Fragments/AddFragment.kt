package com.example.quicknotes.Fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.quicknotes.R
import com.example.quicknotes.databinding.FragmentAddBinding
import com.example.quicknotes.entities.Note
import com.example.quicknotes.viewmodles.VModle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddFragment : Fragment() {

    private lateinit var  Day:String
    private lateinit var  Date:String
    private  lateinit var Month:String
    private  lateinit var Year:String
    private lateinit var viewmodle:VModle
    private lateinit var priority:String
    private var sigininaccount:GoogleSignInAccount?=null
    private lateinit var fragmentAddBinding: FragmentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sigininaccount=GoogleSignIn.getLastSignedInAccount(requireContext())

    4

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddBinding = FragmentAddBinding.inflate(inflater)
        viewmodle = ViewModelProvider(requireActivity()).get(VModle::class)
        val timePick = fragmentAddBinding.timePick
        fragmentAddBinding.M.alpha=0.2f
        fragmentAddBinding.L.alpha=0.2f
        fragmentAddBinding.H.alpha=1.0f
        priority="H"
        timePick.setOnClickListener({
            pickdate()
        })
        setAlpha(fragmentAddBinding)
        fragmentAddBinding.submit.setOnClickListener {
            val title = fragmentAddBinding.titleMain.text.toString()
            val content = fragmentAddBinding.contentMain.text.toString()
            try {
                if ((!title.isNullOrBlank() and !content.isNullOrBlank() and !priority.isNullOrBlank() and !Day.isNullOrBlank() and !Month.isNullOrBlank() and !Date.isNullOrBlank())) {
                    viewmodle.add(
                        Note(
                            userId = sigininaccount?.id!!,
                            note = content,
                            priority = priority,
                            date = Date,
                            month = Month,
                            title = title,
                            stauts = false,
                            day = Day,
                            year = Year
                        )
                    )
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frames,List_data())
                        .commit()
                    
                }else{
                    Toast.makeText(requireContext(),"Priority or Title , Content is empty", Toast.LENGTH_SHORT).show()
                }
            }catch (exception:UninitializedPropertyAccessException){
                Toast.makeText(requireContext(),""+exception.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
        
        
        return fragmentAddBinding.root
    }

    fun setAlpha(fragmentAddBinding: FragmentAddBinding){

        fragmentAddBinding.H.setOnClickListener{
            priority="H"
            fragmentAddBinding.M.alpha=0.2f
            fragmentAddBinding.L.alpha=0.2f
            fragmentAddBinding.H.alpha=1.0f

        }
        fragmentAddBinding.M.setOnClickListener{
            priority="M"
            fragmentAddBinding.M.alpha=1.0f
            fragmentAddBinding.L.alpha=0.2f
            fragmentAddBinding.H.alpha=0.2f

        }
        fragmentAddBinding.L.setOnClickListener{
            priority="L"
            fragmentAddBinding.M.alpha=0.2f
            fragmentAddBinding.L.alpha=1.0f
            fragmentAddBinding.H.alpha=0.2f

        }
    }

    fun pickdate(){
        val calendar = Calendar.getInstance()
        val result = mutableListOf<String>()
        val datePickerDialog = DatePickerDialog(requireContext(),
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                calendar.set(selectedYear, selectedMonth, selectedDayOfMonth)
                val dateFormat = SimpleDateFormat("EEE MMM dd yyyy", Locale.getDefault())
                val selectedDate = dateFormat.format(calendar.time)
                result.addAll(selectedDate.split(" "))
                Log.e("Date", result.toString())
                if (!result.isEmpty()) {
                    Day = result[0]
                    Month = result[1]
                    Date = result[2]
                    Year = result[3]
                }
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH) )

        datePickerDialog.show()

    }

}