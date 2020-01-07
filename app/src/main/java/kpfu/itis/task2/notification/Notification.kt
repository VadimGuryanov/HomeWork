package kpfu.itis.task2.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import kpfu.itis.task2.R
import kpfu.itis.task2.activites.SongActivity
import kpfu.itis.task2.components.Song
import kpfu.itis.task2.service.MusicService
import kpfu.itis.task2.utils.SongPlayingInformation


object NotificationShower {

    private const val CHANNEL_ID = "127"
    private const val NOTIFICATION_ID = 27
    const val EXTRA_ACTION = "action"
    const val EXTRA_SONG = "song"
    const val EXTRA_PAUSE = "pause"
    const val EXTRA_PLAY = "play"
    const val EXTRA_PREV = "prev"
    const val EXTRA_NEXT = "next"
    const val EXTRA_CLOSE = "close"

    fun show(notificationManager: NotificationManager,context: Context, songPlaying: SongPlayingInformation?) {
        var intent = Intent(context, MusicService::class.java)
        var builder = NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setWhen(System.currentTimeMillis())
            .setContentTitle(songPlaying?.song?.title)
            .setContentText(songPlaying?.song?.author)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, songPlaying?.song?.cover ?: R.drawable.ic_launcher_foreground))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .addAction(
                R.drawable.ic_close_black_24dp,
                "Close",
                getPendingIntentService(
                    context,
                    intent,
                    3,
                    EXTRA_CLOSE
                )
            )
            .addAction(
                R.drawable.ic_skip_previous_black_24dp,
                "Previous",
                getPendingIntentService(
                    context,
                    intent,
                    0,
                    EXTRA_PREV
                )
            )
            .addAction(
                R.drawable.ic_pause_black_24dp,
                "Pause",
                getPendingIntentService(
                    context,
                    intent,
                    1,
                    EXTRA_PAUSE
                )
            )
            .addAction(
                R.drawable.ic_skip_next_black_24dp,
                "Next",
                getPendingIntentService(
                    context,
                    intent,
                    2,
                    EXTRA_NEXT
                )
            )
            .setContentIntent(
                getPendingIntentActivity(
                    context,
                    4,
                    songPlaying?.song ?: Song("Unknown", "Unknown",
                        "Unknown", "00:00",
                        R.drawable.ic_launcher_foreground, 0),
                    songPlaying?.position ?: -1
                )
            )
        if (songPlaying?.isPlaying == false) {
            builder
                .addAction(
                    R.drawable.ic_play_arrow_black_24dp,
                    "Play",
                    getPendingIntentService(
                        context,
                        intent,
                        1,
                        EXTRA_PLAY
                    )
                )
                .addAction(
                    R.drawable.ic_skip_next_black_24dp,
                    "Next",
                    getPendingIntentService(
                        context,
                        intent,
                        2,
                        EXTRA_NEXT
                    )
                )
        } else {
            builder
                .addAction(
                    R.drawable.ic_pause_black_24dp,
                    "Pause",
                    getPendingIntentService(
                        context,
                        intent,
                        1,
                        EXTRA_PAUSE
                    )
                ).addAction(
                    R.drawable.ic_skip_next_black_24dp,
                    "Next",
                    getPendingIntentService(
                        context,
                        intent,
                        2,
                        EXTRA_NEXT
                    )
                )
                .setOngoing(true)
        }
        var notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    fun showChanel(notificationManager: NotificationManager, context: Context, songPlaying: SongPlayingInformation?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = songPlaying?.song?.title
            val descriptionText = songPlaying?.song?.author
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = notificationManager.getNotificationChannel(CHANNEL_ID) ?:  NotificationChannel(
                CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
        var intent = Intent(context, MusicService::class.java)
        val builder =
            NotificationCompat.Builder(context,
                CHANNEL_ID
            )
                .setSmallIcon(R.drawable.ic_play_circle_outline_black)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(songPlaying?.song?.title)
                .setContentText(songPlaying?.song?.author)
                .addAction(
                    R.drawable.ic_close_black_24dp,
                    "Close",
                    getPendingIntentService(
                        context,
                        intent,
                        3,
                        EXTRA_CLOSE
                    )
                )
                .addAction(
                    R.drawable.ic_skip_previous_black_24dp,
                    "Previous",
                    getPendingIntentService(
                        context,
                        intent,
                        0,
                        EXTRA_PREV
                    )
                )
                .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, songPlaying?.song?.cover ?: R.drawable.ic_launcher_foreground))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(
                    getPendingIntentActivity(
                        context,
                        4,
                        songPlaying?.song ?: Song("Unknown", "Unknown",
                                                     "Unknown", "00:00",
                                                      R.drawable.ic_launcher_foreground, 0),
                        songPlaying?.position ?: -1
                    )
                )
        if (songPlaying?.isPlaying == false) {
            builder
                .addAction(
                    R.drawable.ic_play_arrow_black_24dp,
                    "Play",
                    getPendingIntentService(
                        context,
                        intent,
                        1,
                        EXTRA_PLAY
                    )
                )
                .addAction(
                    R.drawable.ic_skip_next_black_24dp,
                    "Next",
                    getPendingIntentService(
                        context,
                        intent,
                        2,
                        EXTRA_NEXT
                    )
                )
        } else {
            builder
                .addAction(
                    R.drawable.ic_pause_black_24dp,
                    "Pause",
                    getPendingIntentService(
                        context,
                        intent,
                        1,
                        EXTRA_PAUSE
                    )
                ).addAction(
                    R.drawable.ic_skip_next_black_24dp,
                    "Next",
                    getPendingIntentService(
                        context,
                        intent,
                        2,
                        EXTRA_NEXT
                    )
                )
                .setOngoing(true)
        }
        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun getPendingIntentService(context: Context, intent: Intent, requestCode: Int, extra: String) : PendingIntent {
        intent.putExtra(EXTRA_ACTION, extra)
        return PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT)
    }

    private fun getPendingIntentActivity(context: Context, requestCode: Int, song: Song, position: Int) : PendingIntent {
        var intent = SongActivity.getIntent(context, song, position)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.putExtra(
            EXTRA_ACTION,
            EXTRA_SONG
        )
        return PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT)
    }

}
