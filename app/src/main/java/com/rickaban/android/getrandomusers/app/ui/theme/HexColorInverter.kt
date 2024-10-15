package com.rickaban.android.getrandomusers.app.ui.theme

import timber.log.Timber
import kotlin.math.min

typealias HexColor = String
typealias IsDarkened = Boolean

class HexColorInverter(
    private val hexColor: HexColor
) {
    fun invert(): Pair<IsDarkened, HexColor> {
        val colorInt = android.graphics.Color.parseColor(hexColor)
        val sourceRed = android.graphics.Color.red(colorInt)
        val sourceGreen = android.graphics.Color.green(colorInt)
        val sourceBlue = android.graphics.Color.blue(colorInt)

        if(isSourceWithinLightThreshold(sourceRed, sourceGreen, sourceBlue)){
            val newColor = Pair(true, darkenColor(sourceRed, sourceGreen, sourceBlue))
            Timber.e("TESTX $hexColor seems to be a light color... got its dark color equivalent $newColor")
            return newColor
        }else{
            val newColor = Pair(true, lightenColor(sourceRed, sourceGreen, sourceBlue))
            Timber.e("TESTX $hexColor seems to be a dark color... got its light color equivalent $newColor")
            return newColor
        }
    }

    private fun isSourceWithinLightThreshold(sourceRed: Int, sourceGreen: Int, sourceBlue: Int) = sourceRed + sourceGreen + sourceBlue > (17F * 9) * 3

    private fun lightenColor(sourceRed: Int, sourceGreen: Int, sourceBlue: Int): String {
        val targetMinimum = 9
        val hex = 17F
        val actualMinimum = min((sourceRed/hex), min((sourceGreen/hex), (sourceBlue/hex))).toInt()
        Timber.e("TESTX $sourceRed $sourceGreen $sourceBlue max $actualMinimum")

        val red = (sourceRed + ((targetMinimum-actualMinimum) * hex)).coerceAtMost(255f).toInt()
        val green = (sourceGreen + ((targetMinimum-actualMinimum) * hex)).coerceAtMost(255f).toInt()
        val blue = (sourceBlue + ((targetMinimum-actualMinimum) * hex)).coerceAtMost(255f).toInt()
        return String.format("#%02x%02x%02x", red, green, blue)
    }

    private fun darkenColor(sourceRed: Int, sourceGreen: Int, sourceBlue: Int): String {
        val targetMinimum = 3
        val hex = 17F
        val actualMinimum = min((sourceRed/hex), min((sourceGreen/hex), (sourceBlue/hex))).toInt()
        Timber.e("TESTX $sourceRed $sourceGreen $sourceBlue min $actualMinimum")

        val red = (sourceRed - ((actualMinimum-targetMinimum) * hex)).coerceAtMost(255f).toInt()
        val green = (sourceGreen - ((actualMinimum-targetMinimum) * hex)).coerceAtMost(255f).toInt()
        val blue = (sourceBlue - ((actualMinimum-targetMinimum) * hex)).coerceAtMost(255f).toInt()
        return String.format("#%02x%02x%02x", red, green, blue)
    }
}        