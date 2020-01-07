package kpfu.itis.task2.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_song.view.*
import kpfu.itis.task2.R

class SongItemHolder(
    override val containerView: View,
    private val clickLambda: (Song, Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(song: Song, position: Int) {
        containerView.apply {
            tv_title.text = song.title
            tv_description.text = song.description
            tv_author.text = song.author
            tv_time.text = song.time
            iv_song.setImageResource(song.cover)
            item_song.setOnClickListener {
                clickLambda(song, position)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Song, Int) -> Unit) =
            SongItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false),
                clickLambda
            )
    }

}
