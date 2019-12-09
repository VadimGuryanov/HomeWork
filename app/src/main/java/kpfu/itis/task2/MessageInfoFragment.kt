package kpfu.itis.task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_messange_info.*

class MessageInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_messange_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.message_info)
        tv_to_whom_content.text = arguments?.getString(ARG_WHOM) ?: "Whom"
        tv_theme_content.text = arguments?.getString(ARG_THEME) ?: "Theme"
        tv_message_content.text = arguments?.getString(ARG_MESSAGE) ?: "Message"
    }

    companion object {

        private const val ARG_WHOM = "whom"
        private const val ARG_THEME = "theme"
        private const val ARG_MESSAGE = "message"

        fun newInstance(to_whom: String, theme: String, message: String): MessageInfoFragment =
            MessageInfoFragment().apply {
                Bundle().apply {
                    putString(ARG_WHOM, to_whom)
                    putString(ARG_THEME, theme)
                    putString(ARG_MESSAGE, message)
                    arguments = this
                }
            }

    }

}
