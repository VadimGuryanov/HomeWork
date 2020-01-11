package kpfu.itis.task2.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kpfu.itis.task2.R
import kpfu.itis.task2.fragments.TodosFragment
import kpfu.itis.task2.interfaces.INavigator

class MainActivity : AppCompatActivity(), INavigator,  CoroutineScope by MainScope() {
    
    private val PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissionWithRationale()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.container_main, TodosFragment.newInstance())
                commit()
            }
        }
    }

    override fun onBackPressed() {
        when {
            fragmentManager.backStackEntryCount > 0 -> fragmentManager.popBackStack()
            else -> super.onBackPressed()
        }
    }

    override fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container_main, fragment)
            addToBackStack(fragment::class.java.name)
            commit()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var allowed = false
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> allowed = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (!allowed) showPermsOnSetting()
    }


    private fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            val message = getString(R.string.snack_message)
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.grant).toUpperCase()) {requestPerms()}.show()
        } else {
            requestPerms()
        }
    }

    private fun showPermsOnSetting() {
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.snack_message_settings), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.settings).toUpperCase()) { openApplicationSettings() }.show()
    }

    private fun openApplicationSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName")
        )
        startActivityForResult(appSettingsIntent, PERMISSION_REQUEST_CODE)
    }

    private fun requestPerms() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(permissions, PERMISSION_REQUEST_CODE)
        }
    }

}
