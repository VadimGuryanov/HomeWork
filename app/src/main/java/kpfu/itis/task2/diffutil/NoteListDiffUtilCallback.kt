package kpfu.itis.task2.diffutil

import androidx.recyclerview.widget.DiffUtil
import kpfu.itis.task2.essence.Note

class NoteListDiffUtilCallback(
    private var oldItems: List<Note>,
    private var newItems: List<Note>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].title == newItems[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]

}
