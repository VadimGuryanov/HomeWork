package kpfu.itis.task2.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import kpfu.itis.task2.R
import kpfu.itis.task2.utils.MusicDAO
import kpfu.itis.task2.utils.MusicDAO.Companion.mediaPlayer
import kpfu.itis.task2.notification.NotificationShower.EXTRA_CLOSE
import kpfu.itis.task2.notification.NotificationShower.EXTRA_NEXT
import kpfu.itis.task2.notification.NotificationShower.EXTRA_PAUSE
import kpfu.itis.task2.notification.NotificationShower.EXTRA_PLAY
import kpfu.itis.task2.notification.NotificationShower.EXTRA_PREV
import kpfu.itis.task2.notification.NotificationShower.show
import kpfu.itis.task2.notification.NotificationShower.showChanel
import kpfu.itis.task2.repository.SongRepository
import kpfu.itis.task2.utils.SongPlayingInformation

class MusicService : Service() {

    private val mBinder: IBinder = LocalBinder()
    private lateinit var notificationManager: NotificationManager
    private var songPlaying: SongPlayingInformation? = null

    class LocalBinder : Binder() {
        fun getService() = MusicService()
    }

    override fun onBind(intent: Intent?): IBinder? {
        songPlaying = MusicDAO.getPlayingSong()
        var music = songPlaying?.song?.music ?: R.raw.already_dead
        if (mediaPlayer != null) {
            mediaPlayer?.pause()
        }
        if (mediaPlayer?.isPlaying != true) {
            mediaPlayer = MediaPlayer.create(applicationContext, music)
            mediaPlayer?.start()
        }
        return mBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var action = intent?.getStringExtra("action") ?: ""
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        when (action) {
            EXTRA_PAUSE -> {
                mediaPlayer?.pause()
                updateNotification(true)
                Log.e("extra", action)
            }
            EXTRA_PLAY -> {
                mediaPlayer?.start()
                updateNotification(false)
                Log.e("extra", action)
            }
            EXTRA_PREV -> {
                prev(application.applicationContext)
                updateNotification(false)
                Log.e("extra", action)
            }
            EXTRA_NEXT -> {
                next(application.applicationContext)
                updateNotification(false)
                Log.e("extra", action)
            }
            EXTRA_CLOSE -> close()
            else -> {}
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun play(context: Context, music: Int) {
        songPlaying?.isPlaying = true
        when {
            mediaPlayer == null -> {
                mediaPlayer = MediaPlayer.create(context, music)
                mediaPlayer?.start()
            }
            mediaPlayer?.isPlaying == true -> {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(context, music)
                mediaPlayer?.start()
            }
            else -> mediaPlayer?.start()
        }
    }

    fun pause() {
        songPlaying?.isPlaying = false
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    fun next(context: Context) {
        songPlaying = MusicDAO.getPlayingSong()
        var i = songPlaying?.position ?: 0
        if (SongRepository.songsList.size == i + 1) {
            songPlaying?.position = 0
        } else {
            songPlaying?.position = i + 1
        }
        nextOrPrev(context)
    }

    fun prev(context: Context) {
        songPlaying = MusicDAO.getPlayingSong()
        if (songPlaying?.position == 0) {
            songPlaying?.position = SongRepository.songsList.size - 1
        } else {
            songPlaying?.position = (songPlaying?.position ?: 1) - 1
        }
        nextOrPrev(context)
    }

    private fun nextOrPrev(context: Context) {
        songPlaying?.song = SongRepository.songsList[songPlaying?.position ?: 0]
        songPlaying?.isPlaying = true
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, songPlaying?.song?.music ?: R.raw.already_dead)
        mediaPlayer?.start()
    }

    fun close() {
        songPlaying?.isPlaying = false
        mediaPlayer?.release()
        mediaPlayer = null
        notificationManager.cancelAll()
    }

    private fun updateNotification(isPause: Boolean) {
        songPlaying?.isPlaying = !isPause
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            showChanel(notificationManager, this, songPlaying)
        } else {
            show(notificationManager, this, songPlaying)
        }
    }

}
