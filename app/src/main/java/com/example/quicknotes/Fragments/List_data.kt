package com.example.quicknotes.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quicknotes.R
import com.example.quicknotes.RecyclerAdapter.NotesListAdapter
import com.example.quicknotes.databinding.FragmentListDataBinding
import com.example.quicknotes.entities.Note
import com.example.quicknotes.viewmodles.VModle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class List_data : Fragment() {


    private var list = mutableListOf<Note>()
    public lateinit var notesListAdapter: NotesListAdapter
    private lateinit var fragmentListDataBinding: FragmentListDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var vmodle = ViewModelProvider(requireActivity()).get(VModle::class)
        notesListAdapter = NotesListAdapter(vmodle,list)

        fragmentListDataBinding = FragmentListDataBinding.inflate(inflater)

        var recycler = fragmentListDataBinding.recyclerview
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = notesListAdapter

        vmodle.allnotes.observe(viewLifecycleOwner) {
            if (!list.isEmpty()) {
                list.clear()
            }
            list.addAll(it)
            notesListAdapter.notifyDataSetChanged()
        }
        fragmentListDataBinding.addtask.setOnClickListener{
             parentFragmentManager.beginTransaction()
                 .replace(R.id.frames,AddFragment())
                 .commit()
        }

        return fragmentListDataBinding.root
    }


}