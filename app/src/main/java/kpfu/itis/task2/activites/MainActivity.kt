package kpfu.itis.task2.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kpfu.itis.task2.R
import kpfu.itis.task2.components.Song
import kpfu.itis.task2.fragments.SongsFragment

class MainActivity : AppCompatActivity(), SongsFragment.IMusicPlayer {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun playSong(song: Song, position: Int) {
        startActivity(SongActivity.getIntent(this, song, position))
    }

}
