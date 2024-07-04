package com.example.quicknotes

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.databinding.Bindable
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknotes.R.id.content_edit
import com.example.quicknotes.R.id.submit_edit
import com.example.quicknotes.databinding.LayoutNoteBinding
import com.example.quicknotes.entities.Note
import com.example.quicknotes.viewmodles.VModle
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Suppress("UNREACHABLE_CODE")

class NotesListAdapter (public var vModle: VModle,public  var notes:List<Note>): RecyclerView.Adapter<NotesListAdapter.Holder>() {


    class Holder(public var vModle: VModle, var notes: List<Note>, var layoutNoteBinding: LayoutNoteBinding): RecyclerView.ViewHolder(layoutNoteBinding.root) {
        private lateinit var Day:String
        private lateinit var Date:String
        private lateinit var Year:String
        private lateinit var Month:String
        private lateinit var priority:String
        init {
               layoutNoteBinding.popup.setOnClickListener {
                   var menu = PopupMenu(it.context, layoutNoteBinding.popup, R.menu.menus)
                   menu.inflate(R.menu.menus)

                   menu.setOnMenuItemClickListener {
                       when (it.itemId) {
                           R.id.dlt -> {
                               vModle.delete(notes[adapterPosition])
                               true
                           }
                           R.id.status ->{
                               val note = notes[adapterPosition]
                               note.stauts = true
                               vModle.update(note)
                               true
                           }
                           R.id.edit->{
                               val note = notes[adapterPosition]

                               var dialogue = Dialog(layoutNoteBinding.root.context)
                               dialogue.setContentView(R.layout.layout_addnote2)
                               dialogue.window?.setBackgroundDrawableResource(android.R.color.transparent);
                               val pick_date = dialogue.findViewById<Button>(R.id.time_pick_edit)

                               Day=note.day
                               Year=note.year
                               Month=note.month
                               Date=note.date
                               priority=note.priority

                               val H = dialogue.findViewById<CardView>(R.id.H_edit)
                               val M = dialogue.findViewById<CardView>(R.id.M_edit)
                               val L = dialogue.findViewById<CardView>(R.id.L_edit)
                               val content = dialogue.findViewById<TextInputEditText>(content_edit)
                               val title = dialogue.findViewById<TextInputEditText>(R.id.title_edit)
                               val submit = dialogue.findViewById<Button>(submit_edit)
                               content.setText(note.note)
                               title.setText(note.title)
                               setPreviousPriority(priority,H,M,L)
                               setAlpha(H=H,M=M,L=L)

                               pick_date.setOnClickListener{
                                   pickdate(dialogue.context,note.day,note.month,note.year,note.date)
                               }
                               submit.setOnClickListener{
                                   try {
                                       if ((!title.text.isNullOrBlank() and !content.text.isNullOrBlank() and !priority.isNullOrBlank() and !Day.isNullOrBlank() and !Month.isNullOrBlank() and !Date.isNullOrBlank())) {
                                             var con = content.text.toString()
                                             var ttle = title.text.toString()
                                             note.title=ttle
                                             note.priority=priority
                                             note.note=con
                                             note.date=Date
                                             note.year=Year
                                             note.day=Day
                                             note.month=Month
                                             vModle.update(note)
                                             dialogue.dismiss()

                                       }else{
                                        //   Toast.makeText(applicationContext,"Priority or Title , Content is empty",Toast.LENGTH_SHORT).show()
                                       }
                                   }catch (exception:UninitializedPropertyAccessException){
                                     //  Toast.makeText(applicationContext,"TimeLine not set",Toast.LENGTH_SHORT).show()
                                   }
                               }


                               
                               dialogue.show()

                               true
                           }
                           else -> {
                               true
                           }
                       }

                   }

                   menu.show()
               }
           }
        fun setPreviousPriority(p:String,H:CardView,M:CardView,L:CardView){
            if (p.equals("H")){
                priority="H"
                M.alpha=0.2f
                L.alpha=0.2f
                H.alpha=1.0f
            }else if (p.equals("M")){
                priority="M"
                M.alpha=1.0f
                H.alpha=0.2f
                L.alpha=0.2f
            }else{
                priority="L"
                M.alpha=0.2f
                H.alpha=0.2f
                L.alpha=1.0f
            }
        }
        fun setAlpha(H:CardView,M:CardView,L:CardView){

            H.setOnClickListener({
                priority="H"
                M.alpha=0.2f
                L.alpha=0.2f
                H.alpha=1.0f

            })
            M.setOnClickListener({
                priority="M"
                M.alpha=1.0f
                L.alpha=0.2f
                H.alpha=0.2f

            })
            L.setOnClickListener({
                priority="L"
                M.alpha=0.2f
                L.alpha=1.0f
                H.alpha=0.2f

            })
        }
        fun pickdate(context: Context, day: String, month: String, year: String, date: String,){
            val calendar = Calendar.getInstance()
            var format = SimpleDateFormat("EEE MMM dd yyyy",Locale.getDefault())
            var date =format.parse("$day $month $date $year")
            date?.let {
                calendar.time=it
            }
            val result = mutableListOf<String>()
            val datePickerDialog = DatePickerDialog(context,
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        return NotesListAdapter.Holder(vModle,notes,LayoutNoteBinding.inflate(layoutInflater,parent,false))
    }

    override fun getItemCount(): Int {
       return notes.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (notes.size>0) {
            holder.layoutNoteBinding.notes = notes.get(position)
            holder.layoutNoteBinding.executePendingBindings()
        }
    }
}