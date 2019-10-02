package kpfu.itis.task2.game

import android.util.Log

//команда с консоли////////////////////
private var command: String = "rules"//
///////////////////////////////////////

fun menu() {
    var flag = true
    while(flag) {
        printMes("Выберите одно из команд: game, rules, exit: ")
        printMes(command)
        when (command) {
            "game"  -> {
                Game().game(); command = "exit"}
            "rules" -> {
                getRules()
                command = "game"
                menu()
            }
            "exit"  -> flag = false
            else    -> printMes("Неправильная комманда")
        }
    }
}

private fun getRules() {
    printMes("-----------------------------------------------")
    printMes("- Вводишь размер своей атаки 1-10, чем больше -")
    printMes("- атака тем меньше вероятность попадания.     -")
    printMes("-----------------------------------------------")
}

inline fun printMes(mes: String) {
    //Thread.sleep(500)
    Log.i("print", mes)
}