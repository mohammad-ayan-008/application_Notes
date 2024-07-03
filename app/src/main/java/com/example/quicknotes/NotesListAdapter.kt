package com.example.quicknotes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknotes.databinding.LayoutNoteBinding
import com.example.quicknotes.entities.Note
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")

class NotesListAdapter (public  var notes:List<Note>): RecyclerView.Adapter<NotesListAdapter.Holder>() {

    class Holder(public  var layoutNoteBinding: LayoutNoteBinding): RecyclerView.ViewHolder(layoutNoteBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        return NotesListAdapter.Holder(LayoutNoteBinding.inflate(layoutInflater,parent,false))
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