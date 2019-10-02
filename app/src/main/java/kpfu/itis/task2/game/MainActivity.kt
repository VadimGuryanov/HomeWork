package kpfu.itis.task2.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kpfu.itis.task2.R

/*
    Идея была вводить в консоле,
    но я не нашел способ это сделать в андроид студии
    поэтому ввел данные зарание.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menu()
    }
}
