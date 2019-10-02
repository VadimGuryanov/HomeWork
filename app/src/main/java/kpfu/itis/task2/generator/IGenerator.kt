package kpfu.itis.task2.generator

import kpfu.itis.task2.essences.demon.Demon

interface IGenerator {
    fun generate_demons()
    fun random_demon() : Demon
}