package kpfu.itis.task2.view_holders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note.view.*
import kpfu.itis.task2.R
import kpfu.itis.task2.essence.Note

class NoteItemHolder(
    override val containerView: View,
    private val clickLambda: (Note) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(note: Note) {
        containerView.apply {
            tv_title.text = note.title
            if (note.content.length > 115) {
                tv_content.text = note.content.substring(0, 100) + "..."
            } else {
                tv_content.text = note.content
            }
            ib_delete.setOnClickListener {
                clickLambda(note)
            }
        }
    }

    fun updateFromBundle(bundle: Bundle?) {
        bundle?.apply {
            bind(
                Note(
                    getString(KEY_TITLE) ?: "default",
                    getString(KEY_CONTENT) ?: "default"
                )
            )
        }
    }

    companion object {

        val KEY_TITLE = "title"
        val KEY_CONTENT = "content"

        fun create(parent: ViewGroup, clickLambda: (Note) -> Unit) =
            NoteItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false),
                clickLambda
            )
    }

}
