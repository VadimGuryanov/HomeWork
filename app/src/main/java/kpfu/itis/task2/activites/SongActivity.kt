package kpfu.itis.task2.activites

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_song.*
import kpfu.itis.task2.R
import kpfu.itis.task2.components.Song
import kpfu.itis.task2.service.MusicService

class SongActivity : AppCompatActivity() {

    private var mService: MusicService? = null
    private var mBound = false
    private var music = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        tv_title.text = intent.getStringExtra("title")
        tv_description.text = intent.getStringExtra("description")
        tv_author.text = intent.getStringExtra("author")
        tv_time.text = intent.getStringExtra("time")
        iv_song.setImageResource(intent.getIntExtra("avatar", R.drawable.ic_launcher_foreground))
        music = intent.getIntExtra("music", R.raw.already_dead)
        iv_play.setOnClickListener {
            iv_play.visibility = View.GONE
            iv_pause.visibility = View.VISIBLE
            play(it, music)
        }
        iv_pause.setOnClickListener {
            iv_play.visibility = View.VISIBLE
            iv_pause.visibility = View.GONE
            pause(it, music)
        }
        var intent = Intent(this, MusicService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        play(View(this), music)
    }

    override fun onStart() {
        super.onStart()
//        var intent = Intent(this, MusicService::class.java)
//        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        Log.e("mservice", (mService == null).toString())
    }

//    override fun onStop() {
//        super.onStop()
//        if (mBound) {
//            unbindService(mConnection)
//            mBound = false
//        }
//    }

    override fun onDestroy() {
        Log.e("des", "ex")
        super.onDestroy()
    }

    fun play(view: View, music: Int) {
        Log.e("mservice", (mService == null).toString())
        Log.e("play", "go")
        mService?.play(view, music)
    }

    fun pause(view: View, music: Int) {
        Log.e("pause", "go")
        mService?.pause(view, music)
    }


    private val mConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            var binder = service as MusicService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

    }


    companion object {

        fun getIntent(context: Context, song: Song) =
            Intent(context, SongActivity::class.java).apply {
                putExtra("title", song.title)
                putExtra("description", song.description)
                putExtra("author", song.author)
                putExtra("time", song.time)
                putExtra("avatar", song.cover)
                putExtra("music", song.music)
            }

    }

}