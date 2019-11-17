package kpfu.itis.task2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_notes.*
import kpfu.itis.task2.R
import kpfu.itis.task2.adapters.NoteAdapter
import kpfu.itis.task2.essence.Note
import kpfu.itis.task2.repository.List.Companion.listNote

class NotesFragment : Fragment() {

    private var adapterNote: NoteAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        activity?.title = getString(R.string.notes)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterNote = NoteAdapter(listNote()) { note ->
            onDelete(note)
        }
        rv_notes.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterNote
        }
        fab_add_note.setOnClickListener {
            NoteDialog.show(checkNotNull(activity).supportFragmentManager) { note, i ->
                onPositiveClick(note, i)
            }
        }
        setRecyclerViewItemTouchListener()
    }

    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                adapterNote?.delete(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rv_notes)
    }

    fun onPositiveClick(note: Note, i: String) {
        adapterNote?.add(note, i)
    }

    fun onDelete(note: Note) {
        adapterNote?.delete(note)
    }

    companion object {
        fun newInstance(): NotesFragment = NotesFragment()
    }

}
