package kpfu.itis.task2.adapters

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kpfu.itis.task2.diffutil.NoteListDiffUtilCallback
import kpfu.itis.task2.diffutil.Notediffutil
import kpfu.itis.task2.essence.Note
import kpfu.itis.task2.view_holders.NoteItemHolder

class NoteAdapter(
    private var dataSource: List<Note>,
    private val itemClickLambda: (Note) -> Unit
) : ListAdapter<Note, NoteItemHolder>(Notediffutil) {

    override fun onBindViewHolder(holder: NoteItemHolder, position: Int) =
        holder.bind(dataSource[position])

    override fun getItemCount(): Int = dataSource.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemHolder =
        NoteItemHolder.create(
            parent,
            itemClickLambda
        )

    override fun onBindViewHolder(
        holder: NoteItemHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val bundle = payloads[0] as? Bundle
            holder.updateFromBundle(bundle)
        }
    }

    private fun updateList(newList: List<Note>) {
        val result = DiffUtil.calculateDiff(NoteListDiffUtilCallback(dataSource, newList), true)
        result.dispatchUpdatesTo(this)
        val temp = dataSource.toMutableList()
        temp.clear()
        temp.addAll(newList)
        dataSource = temp.toList()
    }

    fun add(note: Note, position: String) {
        val temp = dataSource.toMutableList()
        when (true) {
            "" == position, (position.toInt() >= dataSource.size) -> {
                temp.add(note)
                updateList(temp.toList())
            }
            else -> {
                temp.add(position.toInt(), note)
                updateList(temp.toList())
            }
        }
    }

    fun delete(note: Note) {
        val temp = dataSource.toMutableList()
        temp.remove(note)
        updateList(temp.toList())
    }

    fun delete(position: Int) {
        val temp = dataSource.toMutableList()
        temp.removeAt(position)
        updateList(temp.toList())
    }

}
