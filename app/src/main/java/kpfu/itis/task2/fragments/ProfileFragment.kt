package kpfu.itis.task2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kpfu.itis.task2.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        activity?.title = getString(R.string.profile)
        return view
    }

    companion object {
        fun newInstance(): ProfileFragment =
            ProfileFragment()
    }

}
