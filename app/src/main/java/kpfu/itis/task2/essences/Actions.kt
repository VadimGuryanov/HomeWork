package kpfu.itis.task2.essences

interface Actions {
    fun init()
    fun attack(dmg: Int)
    fun miss()
    fun getDmg(dmg: Int)
}