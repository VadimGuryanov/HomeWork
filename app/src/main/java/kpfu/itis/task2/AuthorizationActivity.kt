package kpfu.itis.task2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_authorization.*
import kpfu.itis.task2.MainActivity as MainActivity

class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        et_sign_in_pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                ti_sign_in_pass.error = null
                ti_sign_in_pass.isErrorEnabled = false
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setPasswordError() {
        ti_sign_in_pass.error = getString(R.string.validate_password)
    }

    fun onClickLogin(v : View) {
        progress_bar.visibility = View.VISIBLE
        Thread(Runnable {
            Thread.sleep(3000)
            runOnUiThread {
                progress_bar.visibility = View.INVISIBLE
                if (et_sign_in_pass.text.toString() == PasswordRepository.password) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    setPasswordError()
                }
            }
        }).start()
    }

    fun onClickResetPass(v : View) {
        startActivity(Intent(this, ResetPasswordActivity::class.java))
    }
}
