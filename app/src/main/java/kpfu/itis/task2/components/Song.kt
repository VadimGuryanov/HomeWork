package kpfu.itis.task2.components

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Song(
    var title: String,
    var description: String,
    var author: String,
    var time: String,
    @DrawableRes var cover: Int,
    @RawRes var music: Int
)