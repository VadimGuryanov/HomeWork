package kpfu.itis.task2.utils

import kpfu.itis.task2.components.Song

data class SongPlayingInformation(
    var song: Song?,
    var position: Int,
    var isPlaying: Boolean
)
