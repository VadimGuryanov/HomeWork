package kpfu.itis.task2.view_holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_favorite.view.*
import kpfu.itis.task2.R
import kpfu.itis.task2.adapters.ImageSliderAdapter.Companion.newImageSliderAdapter
import kpfu.itis.task2.essence.Favorite

class FavoriteItemHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(favorite: Favorite) {
        containerView.apply {
            tv_title.text = favorite.title
            tv_description.text = favorite.description
            iv_profile.setImageResource(favorite.title_image)
            vp_images.adapter = newImageSliderAdapter(context, favorite.vp_images)
            indicator.setViewPager(vp_images)
        }
    }

    companion object {
        fun create(parent: ViewGroup) =
            FavoriteItemHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_card_view_favorite,
                    parent,
                    false
                )
            )
    }

}
