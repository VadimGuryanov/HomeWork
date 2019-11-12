package kpfu.itis.task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_message_form.*
import kotlinx.android.synthetic.main.fragment_message_form.view.*

class MessageFormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_message_form, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.message_form)
        btn_send.setOnClickListener {
            fragmentManager.also {
                it?.popBackStack()
                it?.beginTransaction()?.apply {
                    setCustomAnimations(
                        R.anim.enter_from_top,
                        R.anim.enter_from_top,
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                    replace(
                        R.id.fragment_container, MessageInfoFragment.newInstance(
                            view.et_to_whom.text.toString(),
                            view.et_theme.text.toString(),
                            view.et_message.text.toString()
                        ),
                        MainActivity.PREVIOUS_FRAGMENT
                    )
                    addToBackStack(MessageInfoFragment::class.java.name)
                    commit()
                }
            }
        }
    }

    companion object {
        fun newInstance(): MessageFormFragment = MessageFormFragment()
    }

}
