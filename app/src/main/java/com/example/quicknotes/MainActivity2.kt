package com.example.quicknotes

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quicknotes.databinding.LayoutAddnoteBinding
import com.example.quicknotes.entities.Note
import com.example.quicknotes.viewmodles.VModle
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
@Suppress("NAME_SHADOWING")
class MainActivity2 : AppCompatActivity() {
    private lateinit var  Day:String
    private lateinit var  Date:String
    private  lateinit var Month:String
    private  lateinit var Year:String

    private lateinit var priority:String
    private lateinit var layoutAddnoteBinding: LayoutAddnoteBinding

    private val viewmodle:VModle by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutAddnoteBinding = LayoutAddnoteBinding.inflate(layoutInflater)
        setContentView(layoutAddnoteBinding.root)
        val timePick = layoutAddnoteBinding.timePick
        layoutAddnoteBinding.M.alpha=0.2f
        layoutAddnoteBinding.L.alpha=0.2f
        layoutAddnoteBinding.H.alpha=1.0f
        priority="H"
        timePick.setOnClickListener({
              pickdate()

        })
        setAlpha(layoutAddnoteBinding)
        layoutAddnoteBinding.submit.setOnClickListener {
            val title = layoutAddnoteBinding.titleMain.text.toString()
            val content = layoutAddnoteBinding.contentMain.text.toString()
            try {
                if ((!title.isNullOrBlank() and !content.isNullOrBlank() and !priority.isNullOrBlank() and !Day.isNullOrBlank() and !Month.isNullOrBlank() and !Date.isNullOrBlank())) {
                    viewmodle.add(
                        Note(
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
                    finish()
                }else{
                    Toast.makeText(applicationContext,"Priority or Title , Content is empty",Toast.LENGTH_SHORT).show()
                }
            }catch (exception:UninitializedPropertyAccessException){
                Toast.makeText(applicationContext,"TimeLine not set",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setAlpha(layoutAddnoteBinding: LayoutAddnoteBinding){

        layoutAddnoteBinding.H.setOnClickListener({
               priority="H"
               layoutAddnoteBinding.M.alpha=0.2f
               layoutAddnoteBinding.L.alpha=0.2f
               layoutAddnoteBinding.H.alpha=1.0f

        })
        layoutAddnoteBinding.M.setOnClickListener({
            priority="M"
            layoutAddnoteBinding.M.alpha=1.0f
            layoutAddnoteBinding.L.alpha=0.2f
            layoutAddnoteBinding.H.alpha=0.2f

        })
        layoutAddnoteBinding.L.setOnClickListener({
            priority="L"
            layoutAddnoteBinding.M.alpha=0.2f
            layoutAddnoteBinding.L.alpha=1.0f
            layoutAddnoteBinding.H.alpha=0.2f

        })
    }

    fun pickdate(){
        val calendar = Calendar.getInstance()
        val result = mutableListOf<String>()
        val datePickerDialog = DatePickerDialog(this,
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