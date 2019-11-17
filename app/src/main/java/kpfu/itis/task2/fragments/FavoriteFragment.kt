package kpfu.itis.task2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorite.*
import kpfu.itis.task2.R
import kpfu.itis.task2.adapters.FavoriteAdapter
import kpfu.itis.task2.repository.List

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        activity?.title = getString(R.string.favorite)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_favorite.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = FavoriteAdapter(List.listFavorite())
        }
    }

    companion object {
        fun newInstance(): FavoriteFragment =
            FavoriteFragment()
    }

}
