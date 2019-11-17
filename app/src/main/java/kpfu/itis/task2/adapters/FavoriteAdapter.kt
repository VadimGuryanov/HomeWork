package kpfu.itis.task2.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kpfu.itis.task2.essence.Favorite
import kpfu.itis.task2.view_holders.FavoriteItemHolder

class FavoriteAdapter(
    private var dataSource: List<Favorite>
) : RecyclerView.Adapter<FavoriteItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemHolder =
        FavoriteItemHolder.create(parent)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: FavoriteItemHolder, position: Int) =
        holder.bind(dataSource[position])

}
