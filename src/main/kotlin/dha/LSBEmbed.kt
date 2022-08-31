package dha

import java.nio.ByteBuffer
import kotlin.experimental.and

class LSBEmbed(highLow: HighLow) : Embedder(highLow)
{
    override fun embedBit(bit: Boolean): HighLow
    {
        if (bit)
        {
            val watermarkedValue = highLow.high.and(0xFF.toByte())
            return HighLow(watermarkedValue,
                           highLow.low
                          )
        }
        val watermarkedValue = highLow.high.and(0xFE.toByte())
        return HighLow(watermarkedValue,
                       highLow.low
                      )
    }

    override fun restore(bit: Boolean): HighLow
    {
        return HighLow(highLow.high.and(if (bit) 0xFF.toByte() else 0xFE.toByte()),
                       highLow.low
                      )
    }
}