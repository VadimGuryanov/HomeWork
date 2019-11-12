package kpfu.itis.task2

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {}
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.also {
                it.beginTransaction().apply {
                    add(R.id.fragment_container, ProfileFragment.newInstance(), PREVIOUS_FRAGMENT)
                    commit()
                }
            }
        }
        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home ->
                    if (!it.isChecked) navigateTo(ProfileFragment.newInstance(), PREVIOUS_FRAGMENT)
                R.id.nav_gallery ->
                    if (!it.isChecked) navigateTo(GalleryFragment.newInstance(), PREVIOUS_FRAGMENT)
                R.id.nav_favorite ->
                    if (!it.isChecked) navigateTo(FavoriteFragment.newInstance(), PREVIOUS_FRAGMENT)
                R.id.nav_send ->
                    if (!it.isChecked) navigateTo(MessagerFragment.newInstance(), PREVIOUS_FRAGMENT)
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
        supportFragmentManager.apply {
            addOnBackStackChangedListener {
                when (findFragmentByTag(PREVIOUS_FRAGMENT)) {
                    is ProfileFragment ->
                        nav_view.menu.getItem(0).isChecked = true
                    is GalleryFragment ->
                        nav_view.menu.getItem(1).isChecked = true
                    is FavoriteFragment ->
                        nav_view.menu.getItem(2).isChecked = true
                    is MessagerFragment ->
                        nav_view.menu.getItem(3).isChecked = true
                }
            }
        }
    }

    private fun navigateTo(fragment: Fragment, tag: String) {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.fragment_container, fragment, tag)
                addToBackStack(fragment::class.java.name)
                commit()
            }
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        when {
            drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawer(GravityCompat.START)
            fragmentManager.backStackEntryCount > 0 -> fragmentManager.popBackStack()
            else -> super.onBackPressed()
        }
    }

    companion object {
        const val PREVIOUS_FRAGMENT = "previous fragment"
    }

}
