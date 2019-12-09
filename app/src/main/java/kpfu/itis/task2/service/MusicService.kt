package kpfu.itis.task2.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.view.View

class MusicService : Service() {

    private val mBinder: IBinder = LocalBinder()
    private var mediaPlayer: MediaPlayer? = null

    class LocalBinder : Binder() {
        fun getService() = MusicService()
    }

    override fun onBind(intent: Intent?): IBinder? = mBinder

    fun play(view: View, music: Int) {
        if (mediaPlayer == null) {
            Log.e("new", "hi")
            mediaPlayer = MediaPlayer.create(view.context, music)
            mediaPlayer?.setOnPreparedListener {
                it.start()
            }
        } else if (mediaPlayer?.isPlaying == true) {
            if (mediaPlayer?.isLooping != true) {
                Log.e("loop", "true")
                mediaPlayer = MediaPlayer.create(view.context, music)
                mediaPlayer?.start()
            } else {
                Log.e("loop", "false")
            }
        } else {
            Log.e("aaa", "false")
            mediaPlayer?.start()
        }
    }

    fun pause(view: View, music: Int) {
        Log.e("p", "ok")
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    fun close() {

    }

    fun next() {

    }

    fun prev() {

    }

}