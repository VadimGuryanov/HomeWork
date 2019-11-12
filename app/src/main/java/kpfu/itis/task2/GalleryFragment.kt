package kpfu.itis.task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class GalleryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        activity?.title = getString(R.string.menu_gallery)
        return view
    }

    companion object {
        fun newInstance(): GalleryFragment = GalleryFragment()
    }

}
