package dha

import kotlin.math.floor

class PixelPair(val a: Pixel, val b: Pixel)
{
    fun toHighLow(): HighLow
    {
        return HighLow(
                (a.value - b.value).toByte(),
                floor((a.value.toFloat() + b.value) / 2).toInt().toByte(),
                this
                      )
    }
}