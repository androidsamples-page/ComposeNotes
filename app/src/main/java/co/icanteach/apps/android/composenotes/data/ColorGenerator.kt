package co.icanteach.apps.android.composenotes.data

import co.icanteach.apps.android.composenotes.R

object ColorGenerator {

    private val colors = listOf(
        R.color.card_color_1,
        R.color.card_color_2,
        R.color.card_color_3,
        R.color.card_color_4,
        R.color.card_color_5,
        R.color.card_color_6,
        R.color.card_color_7,
        R.color.card_color_8,
        R.color.card_color_9,
        R.color.card_color_10,
        R.color.card_color_11
    )

    fun getColor() = colors.random()
}