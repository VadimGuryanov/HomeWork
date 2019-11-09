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
        tv_to_whom_content.text = arguments?.getString(WHOM) ?: "Whom"
        tv_theme_content.text = arguments?.getString(THEME) ?: "Theme"
        tv_message_content.text = arguments?.getString(MESSAGE) ?: "Message"
    }

    companion object {

        private const val WHOM = "whom"
        private const val THEME = "theme"
        private const val MESSAGE = "message"

        fun newInstance(to_whom: String, theme: String, message: String): MessageInfoFragment =
            MessageInfoFragment().also {
                Bundle().apply {
                    putString(WHOM, to_whom)
                    putString(THEME, theme)
                    putString(MESSAGE, message)
                    it.arguments = this
                }
            }

    }

}
