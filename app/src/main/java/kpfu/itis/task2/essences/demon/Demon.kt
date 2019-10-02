package kpfu.itis.task2.essences.demon

import kpfu.itis.task2.essences.Actions
import kpfu.itis.task2.game.printMes

class Demon(var name: String, var magic: Magic, var hp: Int = 100) : Actions {

    override fun init() {
        printMes("Я демон по имени $name! Меня не одолеть!")
    }

    override fun attack(dmg: Int) {
        printMes("$name : Получай истребитель!")
        when (magic) {
            Magic.FLAMEBLOOD_MAGIC-> {
                if (hp < 100 && (1..100).random()*dmg <= 100) {
                    hp += (dmg*0.5).toInt()
                    if (hp > 100) hp = 100
                    printMes("$name : Лечюсь благодаря своей силе! $hp здоровье!")
                }
            }
            Magic.SPIDER_MAGIC  -> {
                printMes("$name : Мою силу не сдержать, я владею самой сильной магией - ${magic?.ability}")
            }
            Magic.DRUM_MAGIC -> {}
            else -> printMes("$name : Без магии бы давно умер")
        }
    }

    override fun miss() {
        printMes("$name : Какой ты ловкий, истребитель!")
    }

    override fun getDmg(dmg: Int) {
        when {
            (magic == Magic.DRUM_MAGIC && (1..100).random()*dmg <= 100) ->
                printMes("$name : Благодаря магии крови, тебе не за что в меня не попасть")
            (magic == Magic.FLAMEBLOOD_MAGIC && (1..100).random()*dmg <= 100) -> {
                hp += (dmg*0.5).toInt()
            }
            else -> {
                hp -= dmg
                if (hp > 0) {
                    printMes("$name : Это больно, но я демон, заживет! Еще много $hp здоровья")
                }
                else {
                    printMes("$name умер...")
                }
            }
        }
    }
}