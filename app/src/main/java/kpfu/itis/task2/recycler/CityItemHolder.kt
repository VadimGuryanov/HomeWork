package kpfu.itis.task2.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_city.view.*
import kpfu.itis.task2.utils.Helper
import kpfu.itis.task2.R
import kpfu.itis.task2.response.WeatherResponse
import kotlin.math.roundToInt

class CityItemHolder(
    override val containerView: View,
    private val clickLambda: (Int) -> Unit,
    private val downloadPhoto: (ImageView, Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(weatherResponse: WeatherResponse, position: Int) {
        containerView.apply {
            tv_name.text = weatherResponse.name
            tv_description.text = weatherResponse.weather[0].description
            tv_temp.text = weatherResponse.main.temp.roundToInt().toString() + "\u2103"
            tv_temp.setTextColor(Helper.setColor(weatherResponse.main.temp.roundToInt()))
            downloadPhoto(iv_weather, position)
        }
        itemView.setOnClickListener {
            clickLambda(weatherResponse.id)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Int) -> Unit, downloadPhoto: (ImageView, Int) -> Unit) =
            CityItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_card_view_city, parent, false),
                clickLambda, downloadPhoto
            )
    }

}
