package kpfu.itis.task2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
    }

    fun onClickResetPass(v : View) {
        PasswordRepository.password = et_reset_pass.text.toString().trim()
        onBackPressed()
    }

}