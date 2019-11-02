package kpfu.itis.task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: AnimeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = AnimeAdapter(getListAnime()) { Anime ->
            navAnimeActivity(Anime.name, Anime.description, Anime.img)
        }
        rv_anime.adapter = adapter
    }

    private fun navAnimeActivity(name: String, description: String, image: Int) {
        startActivity(AnimeActivity.createIntent(this, name, description, image))
    }

    companion object {
        fun getListAnime(): List<Anime> = arrayListOf(
            Anime(
                "Гуррен-Лаганн",
                "Не верь в себя, верь в мою веру в тебя!",
                R.mipmap.ic_gurranlagan_round
            ),
            Anime("Гуррен-Лаганн", "Симооооооон", R.mipmap.ic_gurranlagan_round),
            Anime("Гуррен-Лаганн", "Братааааааан", R.mipmap.ic_gurranlagan_round),
            Anime(
                "Гуррен-Лаганн",
                "Симон, твой бур пробурит небеса!",
                R.mipmap.ic_gurranlagan_round
            ),
            Anime(
                "Гуррен-Лаганн",
                "Не верь в себя, верь в мою веру в тебя!",
                R.mipmap.ic_gurranlagan_round
            ),
            Anime("Гуррен-Лаганн", "Симооооооон", R.mipmap.ic_gurranlagan_round),
            Anime("Гуррен-Лаганн", "Братааааааан", R.mipmap.ic_gurranlagan_round),
            Anime(
                "Гуррен-Лаганн",
                "Симон, твой бур пробурит небеса!",
                R.mipmap.ic_gurranlagan_round
            ),
            Anime(
                "Гуррен-Лаганн",
                "Не верь в себя, верь в мою веру в тебя!",
                R.mipmap.ic_gurranlagan_round
            ),
            Anime("Гуррен-Лаганн", "Симооооооон", R.mipmap.ic_gurranlagan_round),
            Anime("Гуррен-Лаганн", "Братааааааан", R.mipmap.ic_gurranlagan_round),
            Anime(
                "Гуррен-Лаганн",
                "Симон, твой бур пробурит небеса!",
                R.mipmap.ic_gurranlagan_round
            ),
            Anime(
                "Гуррен-Лаганн",
                "Не верь в себя, верь в мою веру в тебя!",
                R.mipmap.ic_gurranlagan_round
            ),
            Anime("Гуррен-Лаганн", "Симооооооон", R.mipmap.ic_gurranlagan_round),
            Anime("Гуррен-Лаганн", "Братааааааан", R.mipmap.ic_gurranlagan_round),
            Anime(
                "Гуррен-Лаганн",
                "Симон, твой бур пробурит небеса!",
                R.mipmap.ic_gurranlagan_round
            ),
            Anime(
                "Гуррен-Лаганн",
                "Не верь в себя, верь в мою веру в тебя!",
                R.mipmap.ic_gurranlagan_round
            )
        )
    }

}
