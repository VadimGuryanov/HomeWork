package kpfu.itis.task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_anime.*

class AnimeItemHolder(
    override val containerView: View,
    private val clickLambda: (Anime) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(anime: Anime) {
        tv_name.text = anime.name
        tv_description.text = anime.description
        ic_anime.setImageResource(anime.img)
        itemView.setOnClickListener {
            clickLambda(anime)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Anime) -> Unit) =
            AnimeItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false),
                clickLambda
            )
    }

}
