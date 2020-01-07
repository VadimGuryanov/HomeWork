package kpfu.itis.task2.components

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(
    private var dataSource: List<Song>,
    private val clickLambda: (Song, Int) -> Unit
) : RecyclerView.Adapter<SongItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemHolder =
        SongItemHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: SongItemHolder, position: Int) =
        holder.bind(dataSource[position], position)

}
