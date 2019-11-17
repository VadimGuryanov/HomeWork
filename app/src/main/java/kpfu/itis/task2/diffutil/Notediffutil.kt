package kpfu.itis.task2.diffutil

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import kpfu.itis.task2.essence.Note
import kpfu.itis.task2.view_holders.NoteItemHolder.Companion.KEY_CONTENT
import kpfu.itis.task2.view_holders.NoteItemHolder.Companion.KEY_TITLE

object Notediffutil : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Note, newItem: Note): Any? {
        val diffBundle = Bundle()
        if (oldItem.title != newItem.title) {
            diffBundle.putString(KEY_TITLE, newItem.title)
        }
        if (oldItem.content != newItem.content) {
            diffBundle.putString(KEY_CONTENT, newItem.content)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }

}
