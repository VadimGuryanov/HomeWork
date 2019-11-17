package kpfu.itis.task2.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kpfu.itis.task2.R
import kpfu.itis.task2.fragments.FavoriteFragment
import kpfu.itis.task2.fragments.NotesFragment
import kpfu.itis.task2.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            bnv_main.menu.getItem(1).isChecked = true
            supportFragmentManager.beginTransaction().apply {
                add(R.id.container_main, ProfileFragment.newInstance())
                addToBackStack(ProfileFragment::class.java.name)
                commit()
            }
        }
        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_person -> {
                    navigateTo(ProfileFragment.newInstance())
                    true
                }
                R.id.nav_favorite -> {
                    navigateTo(FavoriteFragment.newInstance())
                    true
                }
                R.id.nav_notes -> {
                    navigateTo(NotesFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container_main, fragment)
            addToBackStack(fragment::class.java.name)
            commit()
        }
    }

}
