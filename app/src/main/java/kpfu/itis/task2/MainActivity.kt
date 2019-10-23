package kpfu.itis.task2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_SHARE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        val mess : String = et_message.text.toString()
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mess)
            type = "text/plain"
        }
        setResult(RESULT_OK, sendIntent)
        startActivityForResult(sendIntent, REQUEST_CODE_SHARE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SHARE && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Is Failed message", Toast.LENGTH_SHORT).show()
        }
    }

}
