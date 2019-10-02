package kpfu.itis.task2.essences.fighter

enum class Blade(var ability: String, var description: String) {
    WIND_BLADE("Клинок ветра", "Истрибитель способен уклониться от атаки демона!"),
    FLAME_BLADE("Клинок пламени", "При удачной атаке восстановит здоровье вашему герою равную вашей атаке!"),
    STONE_BLADE("Клинок камня","Может нанести дополнительные 50% урона демону!"),
}