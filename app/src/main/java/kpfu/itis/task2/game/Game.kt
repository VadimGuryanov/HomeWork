package kpfu.itis.task2.game

import kpfu.itis.task2.essences.demon.Magic
import kpfu.itis.task2.essences.fighter.Blade
import kpfu.itis.task2.essences.fighter.Fighter
import kpfu.itis.task2.generator.Generetor

class Game : IGame {

    //команды, которые должны былы вводиться с консоли :
    private val blade: Int = 3                      ///
    private val name: String = "Таджиро Комадо"      ///
    ////////////////////////////////////////////////////

    override fun game() {
        var flag = true
        var fighter: Fighter?
        while (flag) {
            fighter = create()
            flag = battle(fighter)
        }
    }

    override fun create() : Fighter {
        val fighter: Fighter?
        var blade: Blade? = null
        var flag = true
        printMes("Создаем Истрибителя на Демонов!")
        printMes("Введи имя своего истребителя: ")
        printMes(name)
        printMes("Какое оружие хотите?")
        printMes("Введите номер оружия")
        while (flag) {
            printMes("1. ${Blade.WIND_BLADE.ability} ${Blade.WIND_BLADE.description}")
            printMes("2. ${Blade.FLAME_BLADE.ability} ${Blade.FLAME_BLADE.description}")
            printMes("3. ${Blade.STONE_BLADE.ability} ${Blade.STONE_BLADE.description}")
            printMes("------> ${this.blade}")
            when(this.blade) {
                1 -> { blade = Blade.WIND_BLADE; flag = false }
                2 -> { blade = Blade.FLAME_BLADE; flag = false }
                3 -> { blade = Blade.STONE_BLADE; flag = false }
                else -> printMes("Введите правильный номер оружия")
            }
        }
        printMes("Приветствую тебя истребитель!")
        fighter = Fighter(name, blade)
        return fighter
    }

    override fun battle(fighter: Fighter) : Boolean {
        printMes("------------------------------В БОЙ--------------------------------")
        printMes("- Введите урон от 1-10, чтобы атаковать демона, но будь осторожен -")
        printMes("- Чем больше урон, тем больше шанс промазать                      -")
        printMes("-------------------------------------------------------------------")
        var flag = true
        while(flag) {
            val generetor = Generetor()
            generetor.generate_demons()
            val demon = generetor.random_demon()
            fighter.init()
            demon.init()
            if (generetor.demonList.isNotEmpty()) {
                while(fighter.hp > 0 && demon.hp > 0) {

                    var dmg = (1..10).random()
                    printMes("Аттакует ${fighter.name}! ---> $dmg")
                    if (fighter.blade == Blade.STONE_BLADE && (1..100).random()*dmg <= 100) {
                        fighter.attack(dmg)
                        demon.getDmg((dmg * 1.5).toInt())
                    } else if ((1..100).random()*dmg <= 100) {
                        fighter.attack(dmg)
                        demon.getDmg(dmg)
                    } else {
                        fighter.miss()
                    }

                    dmg = (1..10).random()
                    printMes("Аттакует ${demon.name}! ---> $dmg")
                    if (demon.magic == Magic.SPIDER_MAGIC && (1..100).random()*dmg <= 100) {
                        demon.attack(dmg)
                        fighter.getDmg((dmg*1.5).toInt())
                    } else if ((1..100).random()*dmg <= 100) {
                        demon.attack(dmg)
                        fighter.getDmg(dmg)
                    } else {
                        demon.miss()
                    }

                    when {
                        demon.hp < 0 -> printMes("${fighter.name} убил демона")
                        fighter.hp < 0 -> printMes("${demon.name} убил ${fighter.name}")
                    }
                }
            } else {
                printMes("Ты смог убить всех демонов, ${fighter.name}!")
                flag = false
            }
            if (fighter.hp <= 0) flag = false
        }
        return flag
    }

}