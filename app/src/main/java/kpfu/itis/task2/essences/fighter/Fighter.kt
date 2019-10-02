package kpfu.itis.task2.essences.fighter

import kpfu.itis.task2.essences.Actions
import kpfu.itis.task2.game.printMes

class Fighter(var name: String?, var blade: Blade?, var hp: Int = 100) : Actions {

    override fun init() {
        printMes("Я истребитель по имени $name! Смерть демонам!")
    }

    override fun attack(dmg: Int) {
        printMes("$name : Я тебя атакую!")
        when (blade) {
            Blade.FLAME_BLADE -> {
                if (hp < 100 && (1..100).random()*dmg <= 100) {
                    hp += dmg
                    if (hp > 100) hp = 100
                    printMes("$name : Хилимся, живем, ... + $dmg! Подлечился $hp")
                }
            }
            Blade.STONE_BLADE  -> {
                printMes("$name : Больше урона получаешь, потому что в рука у меня ${blade?.ability}")
            }
            Blade.WIND_BLADE -> {}
            else -> printMes("$name : Без оружия бы не пустили")
        }
    }

    override fun miss() {
        printMes("В следующий раз не промахнусь!")
    }

    override fun getDmg(dmg: Int) {
        if (blade == Blade.WIND_BLADE && (1..100).random()*dmg <= 100) {
            printMes("$name : В моей руке ${blade?.ability}, тебе не за что в меня не попасть")
        } else {
            hp -= dmg
            if (hp > 0) {
                printMes("$name : Ай, больно! Осталось $hp здоровья!")
            } else {
                printMes("$name умер...")
            }
        }
    }

}