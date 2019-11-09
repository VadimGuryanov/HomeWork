package kpfu.itis.task2

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

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
        title = nav_view.menu.getItem(0).title
        nav_view.menu.getItem(0).isChecked = true
        if (savedInstanceState == null) {
            supportFragmentManager.also {
                it.beginTransaction().apply {
                    add(R.id.fragment_container, ProfileFragment.newInstance(), "tag")
                    commit()
                }
            }
            addOnBackStackChangedListener()
        }
        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home ->
                    if (!it.isChecked) goProfileFragment()
                R.id.nav_gallery ->
                    if (!it.isChecked) goGalleryFragment()
                R.id.nav_favorite ->
                    if (!it.isChecked) goFavoriteFragment()
                R.id.nav_send ->
                    if (!it.isChecked) goMessangerFragment()
            }
            addOnBackStackChangedListener()
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun goProfileFragment() {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.fragment_container, ProfileFragment.newInstance(), PREVIOUS_FRAGMENT)
                addToBackStack(ProfileFragment::class.java.name)
                commit()
            }
        }
    }

    private fun goGalleryFragment() {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.fragment_container, GalleryFragment.newInstance(), PREVIOUS_FRAGMENT)
                addToBackStack(GalleryFragment::class.java.name)
                commit()
            }
        }
    }

    private fun goMessangerFragment() {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.fragment_container, MessangerFragment.newInstance(), PREVIOUS_FRAGMENT)
                addToBackStack(MessangerFragment::class.java.name)
                commit()
            }
        }
    }

    private fun goFavoriteFragment() {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.fragment_container, FavoriteFragment.newInstance(), PREVIOUS_FRAGMENT)
                addToBackStack(FavoriteFragment::class.java.name)
                commit()
            }
        }
    }

    private fun addOnBackStackChangedListener() {
        supportFragmentManager.apply {
            addOnBackStackChangedListener {
                when (findFragmentByTag(PREVIOUS_FRAGMENT)) {
                    is ProfileFragment -> {
                        nav_view.menu.getItem(0).apply {
                            toolbar.title = title
                            isChecked = true
                        }
                    }
                    is GalleryFragment -> {
                        nav_view.menu.getItem(1).apply {
                            toolbar.title = title
                            isChecked = true
                        }
                    }
                    is FavoriteFragment -> {
                        nav_view.menu.getItem(2).apply {
                            toolbar.title = title
                            isChecked = true
                        }
                    }
                    is MessangerFragment -> {
                        nav_view.menu.getItem(3).apply {
                            toolbar.title = title
                            isChecked = true
                        }
                    }
                    is MessageInfoFragment -> {
                        toolbar.title = "Message information"
                    }
                    is MessageFormFragment -> {
                        toolbar.title = "Message form"
                    }
                }
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
        private const val PREVIOUS_FRAGMENT = "previous fragment"
    }

}
