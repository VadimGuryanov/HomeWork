package kpfu.itis.task2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.view_pager_image.view.*
import kpfu.itis.task2.R

class ImageSliderAdapter(
    var context: Context,
    var favoriteList: List<Int>
) : PagerAdapter() {

    companion object {
        fun newImageSliderAdapter(context: Context, favoriteList: List<Int>): ImageSliderAdapter =
            ImageSliderAdapter(
                context,
                favoriteList
            )
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout =
            LayoutInflater.from(context).inflate(R.layout.view_pager_image, container, false)
        layout.iv_slider.setImageResource(favoriteList[position])
        container.addView(layout, 0)
        return layout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = favoriteList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}