package kpfu.itis.task2.recycler

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kpfu.itis.task2.response.WeatherResponse

class CityAdapter(
    private var dataSource: List<WeatherResponse>,
    private val clickLambda: (Int) -> Unit,
    private val downloadPhoto: (ImageView, Int) -> Unit
) : RecyclerView.Adapter<CityItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemHolder =
        CityItemHolder.create(parent, clickLambda, downloadPhoto)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: CityItemHolder, position: Int) =
        holder.bind(dataSource[position], position)
}
