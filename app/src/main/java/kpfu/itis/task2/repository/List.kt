package kpfu.itis.task2.repository

import kpfu.itis.task2.R
import kpfu.itis.task2.essence.Favorite
import kpfu.itis.task2.essence.Note

class List {

    companion object {
        fun listNote() = listOf(
            Note(
                "Vadim",
                "DiffUtil is a utility class that can calculate the difference between two lists and output a list of update operations that converts"
            ),
            Note(
                "Artem",
                "DiffUtil is a utility class that can calculate the difference between two lists and output a list of update operations that converts"
            ),
            Note(
                "Gosha",
                "DiffUtil is a utility class that can calculate the difference between two lists and output a list of update operations that converts"
            ),
            Note(
                "Maks",
                "DiffUtil is a utility class that can calculate the difference between two lists and output a list of update operations that converts"
            ),
            Note(
                "Sass",
                "DiffUtil is a utility class that can calculate the difference between two lists and output a list of update operations that converts"
            ),
            Note(
                "Kate",
                "DiffUtil is a utility class that can calculate the difference between two lists and output a list of update operations that converts"
            ),
            Note(
                "Vova",
                "DiffUtil is a utility class that can calculate the difference between two lists and output a list of update operations that converts"
            ),
            Note(
                "Kola",
                "DiffUtil is a utility class that can calculate the difference between two lists and output a list of update operations that converts"
            ),
            Note(
                "Pepsi",
                "DiffUtil is a utility class that can calculate the difference between two lists and output a list of update operations that converts"
            ),
            Note(
                "Defender",
                "DiffUtil is a utility class that can calculate the difference between two lists and output a list of update operations that converts"
            )
        )

        fun listFavorite() = listOf(
            Favorite(
                "anime",
                R.drawable.tan1,
                listOf(R.drawable.tan1, R.drawable.tan2, R.drawable.tan3),
                "Anime picture with tenka hyakken jouizumi masamune (tenka hyakken) pisuke long hair single tall image blush looking"
            ),
            Favorite(
                "lofi hip-hop",
                R.drawable.lofi1,
                listOf(
                    R.drawable.lofi1,
                    R.drawable.lofi2,
                    R.drawable.lofi3,
                    R.drawable.lofi4,
                    R.drawable.lofi5
                ),
                "Applying dainty melodies – often on the piano, harp, guitar or saxophone – over a simple 4/4 beat, with artificial vinyl crackles, rain sounds and tape hisses added on top, lo-fi hip-hop is the aural equivalent of sitting by a warm fire on a rainy night."
            ),
            Favorite(
                "panels",
                R.drawable.panel1,
                listOf(R.drawable.panel1, R.drawable.panel2, R.drawable.panel3, R.drawable.panel4),
                "It starts like a stomach ache, Hiding in troubled waters, Emerge into the cradle, And grow into the socket little by little, Hide and seek with his father, hot and cold"
            )
        )
    }
}
