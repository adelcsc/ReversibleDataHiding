package dha

import kotlin.math.floor

class ExpandEmbed(highLow: HighLow) : Embedder(highLow)
{
    override fun embedBit(bit: Boolean): HighLow
    {
        val shifted = highLow.high.times(0x02).plus(if (bit) 0x01 else 0).toByte()
        return HighLow(shifted,
                       highLow.low
                      )
    }

    override fun restore(bit: Boolean): HighLow
    {
        return HighLow(floor(highLow.high.toFloat() / 2).toInt().toByte(),
                       highLow.low,
                       highLow.toPixelPair()
                      )
    }
}