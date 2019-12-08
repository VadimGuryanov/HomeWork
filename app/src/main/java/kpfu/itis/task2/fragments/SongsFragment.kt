package kpfu.itis.task2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_song_list.*
import kpfu.itis.task2.R
import kpfu.itis.task2.components.Song
import kpfu.itis.task2.components.SongAdapter
import kpfu.itis.task2.repository.SongRepository

class SongsFragment : Fragment() {

    private var adapterSong: SongAdapter? = null
    private lateinit var iMusicPlayer: IMusicPlayer


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_song_list, container, false)
        activity?.title = getString(R.string.music)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iMusicPlayer = activity as IMusicPlayer
        adapterSong = SongAdapter(SongRepository.songsList) {
            iMusicPlayer.playSong(it)
        }
        rv_song.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterSong
        }
        var itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rv_song.addItemDecoration(itemDecoration)
    }

    interface IMusicPlayer {
        fun playSong(song: Song)
    }

//    private fun clickSong(song: Song) {
//        if (mediaPlayer == null) {
//            mediaPlayer = MediaPlayer.create(context, song.music)
//            mediaPlayer?.start()
//        } else {
//            mediaPlayer?.stop()
//            mediaPlayer = MediaPlayer.create(context, song.music)
//        }
//    }

//
//    private fun playMusic(song: Song) {
//        if (mediaPlayer == null) {
//            mediaPlayer = MediaPlayer.create(context, song.music)
//        } else if () {
//
//        }
//        var isPlaying = mediaPlayer?.isPlaying ?: false
//        if (!isPlaying) {
//            mediaPlayer = MediaPlayer.create(context, song.music)
//            mediaPlayer?.start()
//        } else {
//            mediaPlayer?.pause()
//        }
//    }

//        if (mediaPlayer.isPlaying && mediaPlayer.) {
//            mediaPlayer.stop()
//            Log.e("tag-stop",mediaPlayer.isPlaying.toString())
//        } else {
//            mediaPlayer.start()
//            Log.e("tag-play",mediaPlayer.isPlaying.toString())
//        }


}
