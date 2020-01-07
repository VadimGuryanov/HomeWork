package kpfu.itis.task2.activites

import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_song.*
import kpfu.itis.task2.notification.NotificationShower.show
import kpfu.itis.task2.notification.NotificationShower.showChanel
import kpfu.itis.task2.R
import kpfu.itis.task2.components.Song
import kpfu.itis.task2.repository.SongRepository
import kpfu.itis.task2.service.MusicService
import kpfu.itis.task2.utils.MusicDAO
import kpfu.itis.task2.utils.SongPlayingInformation


class SongActivity : AppCompatActivity() {

    private var mService: MusicService? = null
    private var mBound = false
    private var music = 0
    private var position = 0
    private var songPlaying: SongPlayingInformation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        tv_title.text = intent.getStringExtra(EXTRA_TITLE)
        tv_description.text = intent.getStringExtra(EXTRA_DESCRIPTION)
        tv_author.text = intent.getStringExtra(EXTRA_AUTHOR)
        tv_time.text = intent.getStringExtra(EXTRA_TIME)
        iv_song.setImageResource(intent.getIntExtra(EXTRA_AVATAR, R.drawable.ic_launcher_foreground))
        music = intent.getIntExtra(EXTRA_MUSIC, R.raw.already_dead)
        position = intent.getIntExtra(EXTRA_POSITION, 0)
        iv_play.setOnClickListener {
            iv_play.visibility = View.GONE
            iv_pause.visibility = View.VISIBLE
            play(music)
        }
        iv_pause.setOnClickListener {
            iv_play.visibility = View.VISIBLE
            iv_pause.visibility = View.GONE
            pause()
        }
        iv_next.setOnClickListener {
            next()
        }
        iv_prev.setOnClickListener {
            prev()
        }
        songPlaying = MusicDAO.getPlayingSong()
        songPlaying?.song = SongRepository.songsList[position]
        songPlaying?.position = position
        songPlaying?.isPlaying = true
    }

    override fun onStart() {
        super.onStart()
        switchMusic()
        var intent = Intent(this, MusicService::class.java)
        showNotification(false)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onResume() {
        super.onResume()
        play(songPlaying?.song?.music ?: 0)
        switchMusic()
    }

    override fun onStop() {
        super.onStop()
        Log.e("stop", "stop")
        if (mBound) {
            unbindService(mConnection)
            mBound = false
        }
    }

    fun play(music: Int) {
        showNotification(false)
        songPlaying?.isPlaying = true
        mService?.play(this, music)
    }

    fun pause() {
        showNotification(true)
        songPlaying?.isPlaying = false
        mService?.pause()
    }

    fun next() {
        mService?.next(this)
        showNotification(false)
        goneImageView()
        switchMusic()
    }

    fun prev() {
        mService?.prev(this)
        showNotification(false)
        goneImageView()
        switchMusic()
    }

    private fun goneImageView() {
        if (iv_play.visibility == View.VISIBLE) {
            iv_play.visibility = View.GONE
            iv_pause.visibility = View.VISIBLE
        }
    }

    private fun switchMusic() {
        songPlaying = MusicDAO.getPlayingSong()
        Log.e("song", songPlaying?.position.toString())
        songPlaying?.apply {
            tv_title.text = song?.title ?: "Unknown"
            tv_description.text = song?.description ?: "Unknown"
            tv_author.text = song?.author ?: "Unknown"
            tv_time.text = song?.time ?: "00:00"
            iv_song.setImageResource(song?.cover ?: R.drawable.ic_launcher_background)
        }
        if (songPlaying?.isPlaying == true) {
            iv_play.visibility = View.GONE
            iv_pause.visibility = View.VISIBLE
        } else {
            iv_play.visibility = View.VISIBLE
            iv_pause.visibility = View.GONE
        }
    }

    private fun showNotification(isPause: Boolean) {
        songPlaying?.isPlaying = !isPause
        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            showChanel(notificationManager, this, songPlaying)
        } else {
            show(notificationManager, this, songPlaying)
        }
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

        private const val EXTRA_TITLE = "title"
        private const val EXTRA_DESCRIPTION = "description"
        private const val EXTRA_AUTHOR = "author"
        private const val EXTRA_TIME = "time"
        private const val EXTRA_MUSIC = "music"
        private const val EXTRA_AVATAR = "avatar"
        const val EXTRA_POSITION = "position"

        fun getIntent(context: Context, song: Song, position: Int) =
            Intent(context, SongActivity::class.java).apply {
                putExtra(EXTRA_TITLE, song.title)
                putExtra(EXTRA_DESCRIPTION, song.description)
                putExtra(EXTRA_AUTHOR, song.author)
                putExtra(EXTRA_TIME, song.time)
                putExtra(EXTRA_AVATAR, song.cover)
                putExtra(EXTRA_MUSIC, song.music)
                putExtra(EXTRA_POSITION, position)
            }

    }

}
