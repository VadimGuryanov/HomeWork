package kpfu.itis.task2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_anime.*

class AnimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime)
        val name = intent?.extras?.getString(KEY_ANIME) ?: "DEFAULT ANIME"
        val description = intent?.extras?.getString(KEY_DESCRIPTION) ?: "DEFAULT DESCRIPTION ANIME"
        val image = intent?.extras?.getInt(KEY_IMAGE) ?: R.mipmap.ic_launcher
        tv_anime_name.text = name
        tv_anime_description.text = description
        ic_anime.setImageResource(image)
    }

    companion object {
        private const val KEY_ANIME = "anime"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_IMAGE = "image"

        fun createIntent(activity: Activity, name: String, description: String, image: Int) =
            Intent(activity, AnimeActivity::class.java).apply {
                putExtra(KEY_ANIME, name)
                putExtra(KEY_DESCRIPTION, description)
                putExtra(KEY_IMAGE, image)
            }
    }

}
