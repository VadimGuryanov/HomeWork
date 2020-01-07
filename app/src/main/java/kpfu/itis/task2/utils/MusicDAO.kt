package kpfu.itis.task2.utils

import android.media.MediaPlayer

class MusicDAO {

    companion object {
        var mediaPlayer: MediaPlayer? = null
        private var songPlaying: SongPlayingInformation? = null
        fun getPlayingSong() : SongPlayingInformation? {
            if (songPlaying == null) {
                songPlaying = SongPlayingInformation(null, -1, false)
            }
            return songPlaying
        }
    }

}
