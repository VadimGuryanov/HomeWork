package kpfu.itis.task2.activity

import android.Manifest
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kpfu.itis.task2.BuildConfig
import kpfu.itis.task2.R
import kpfu.itis.task2.network.ApiFactory
import kpfu.itis.task2.network.WeatherService
import kpfu.itis.task2.recycler.CityAdapter
import kpfu.itis.task2.utils.Const
import kpfu.itis.task2.utils.Downloader
import java.io.IOException

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private var service: WeatherService = ApiFactory.weatherService
    private var lat: Double = 0.0
    private var lon: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        service = ApiFactory.weatherService
        lat = Const.lat.toDouble()
        lon = Const.lon.toDouble()
        getPermission()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_search_view, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem?.actionView as SearchView
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                launch {
                    try {
                        val response = withContext(Dispatchers.IO) {
                            service.weatherByName(query ?: "")
                        }
                        navigateTo(response.id)
                    } catch (ex: IOException) {
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "There is no such city",
                            Snackbar.LENGTH_INDEFINITE
                        ).show()
                    }
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean = false
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun getPermission() {
        val checker = ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
        if (checker != PackageManager.PERMISSION_GRANTED) {
            requestPermissionWithRationale()
        } else {
            getLocation()
        }
    }

    private fun getLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient
            .lastLocation
            .addOnSuccessListener(this) {
                it.apply {
                    if (this != null) {
                        lat = latitude
                        lon = longitude
                    } else {
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "Error while getting location data",
                            Snackbar.LENGTH_INDEFINITE
                        ).show()
                    }
                }
                getListWeather()
            }
            .addOnFailureListener(this) {
                getListWeather()
            }
    }

    private fun getListWeather() {
        launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.weathersCitiesInCycle(lat,lon,Const.cnt_city)
                }
                rv_citys.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = CityAdapter(response.list,
                        {
                            navigateTo(it)
                        }) {
                            iv_weather, id ->
                        Downloader.initPicasso(this@MainActivity)
                            .load(BuildConfig.PHOTO_URI + response.list[id].weather[0].icon + "@2x.png")
                            .resize(Const.size_150, Const.size_150)
                            .into(iv_weather)
                    }
                }
            }
            catch (ex: IOException) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    ex.message.toString(),
                    Snackbar.LENGTH_INDEFINITE
                ).show()
            }
        }
    }

    fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            val message = getString(R.string.snack_message)
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.grant).toUpperCase()) { v: View? -> requestPerms() }
                .show()
        } else {
            requestPerms()
        }
    }

    private fun requestPerms() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(permissions, Const.permission_request_code)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        var allowed = false
        if (requestCode == Const.permission_request_code) {
            allowed = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (allowed) getLocation() else showPermsOnSetting()

    }

    fun showPermsOnSetting() {
        getListWeather()
        Snackbar.make(
            findViewById(android.R.id.content),
            getString(R.string.storage_permissions_not_granted),
            Snackbar.LENGTH_LONG)
            .setAction(R.string.settings) { v: View? -> openApplicationSettings() }
            .show()
    }

    private fun openApplicationSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName")
        )
        startActivityForResult(appSettingsIntent, Const.permission_request_code)
    }


    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }

    private fun navigateTo(id: Int) {
        startActivity(
            CityWeatherActivity.newIntent(
                this@MainActivity,
                id
            )
        )
    }

}
