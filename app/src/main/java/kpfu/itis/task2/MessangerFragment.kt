package kpfu.itis.task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_messanger.*

class MessangerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_messanger, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_send.setOnClickListener {
            fragmentManager.also {
                it?.beginTransaction()?.apply {
                    setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                    )
                    replace(R.id.fragment_container, MessageFormFragment.newInstance(), "tag")
                    addToBackStack(MessageFormFragment::class.java.name)
                    commit()
                }
            }
        }
    }

    companion object {
        fun newInstance(): MessangerFragment = MessangerFragment()
    }

}
