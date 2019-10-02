package kpfu.itis.task2.game

import kpfu.itis.task2.essences.fighter.Fighter

interface IGame {
    fun game()
    fun create() : Fighter
    fun battle(fighter: Fighter) : Boolean
}