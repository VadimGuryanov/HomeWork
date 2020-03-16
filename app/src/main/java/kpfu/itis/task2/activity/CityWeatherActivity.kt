package kpfu.itis.task2.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_city_weather.*
import kotlinx.android.synthetic.main.item_details_city.*
import kotlinx.coroutines.*
import kpfu.itis.task2.*
import kpfu.itis.task2.network.ApiFactory
import kpfu.itis.task2.utils.Const
import kpfu.itis.task2.utils.Downloader
import kpfu.itis.task2.utils.Helper
import java.io.IOException
import kotlin.math.roundToInt

class CityWeatherActivity: AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather)

        val service = ApiFactory.weatherService
        val id = intent.getIntExtra(EXTRA_ID, 0)
        launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.weatherById(id)
                }
                this@CityWeatherActivity.title = response.name
                tv_temp.text = "${response.main.temp.roundToInt()} + \u2103"
                Downloader.initPicasso(this@CityWeatherActivity)
                    .load("http://openweathermap.org/img/wn/" + response.weather[0].icon + "@2x.png")
                    .resize(Const.size_300, Const.size_300)
                    .into(iv_weather)
                val max = response.main.tempMax.roundToInt().toString()
                val min = response.main.tempMin.roundToInt().toString()
                tv_temp_value.text = "$max/$min\u2103"
                tv_humidity_value.text = response.main.humidity.toString() + "%"
                tv_pressure_value.text = response.main.pressure.toString() + "hPa"
                tv_wind_value.text = response.wind.speed.toString() + "m/s " + Helper.degressWind(response.wind.deg)
            } catch (ex: IOException) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    ex.message.toString(),
                    Snackbar.LENGTH_INDEFINITE
                ).show()
            }
        }


    }

    companion object {
        private const val EXTRA_ID = "id"

        fun newIntent(context: Context, id: Int): Intent =
            Intent(context, CityWeatherActivity::class.java).putExtra(
                EXTRA_ID, id)
    }

}
