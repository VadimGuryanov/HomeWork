package kpfu.itis.task2.generator

import kpfu.itis.task2.essences.demon.Demon
import kpfu.itis.task2.essences.demon.Magic
import kpfu.itis.task2.game.printMes

class Generetor : IGenerator {

    val demonList: ArrayList<Demon> = ArrayList()
    private var j = 2

    override fun generate_demons() {
        val demon1 = Demon("Энму", Magic.FLAMEBLOOD_MAGIC)
        val demon2 = Demon("Аказа", Magic.DRUM_MAGIC)
        val demon3 = Demon("Спайдоро", Magic.SPIDER_MAGIC)
        demonList.run {
            add(demon1)
            add(demon2)
            add(demon3)
        }
    }

    override fun random_demon() : Demon {
        val i = (0..j).random()
        printMes(i.toString())
        val demon: Demon = demonList[i]
        demonList.remove(demon)
        j--
        return demon
    }

}