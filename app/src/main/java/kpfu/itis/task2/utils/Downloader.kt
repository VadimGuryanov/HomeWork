package kpfu.itis.task2.utils

import android.content.Context
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso

object Downloader {

    fun initPicasso(context: Context) : Picasso {
        return Picasso.Builder(context)
            .downloader(OkHttpDownloader(context))
            .build()
    }

}
