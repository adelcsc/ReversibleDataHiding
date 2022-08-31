package dha

import java.util.BitSet
import kotlin.experimental.and

abstract class ARegion(val highLows: List<HighLow>)
{

    open fun getCapacity(): Int
    {
        return highLows.size
    }

    fun embedData(data: BitSet): ARegion
    {
        if (data.size() > getCapacity())
            throw Exception("Data is Larger than this region can embed")
        var lastI = 0
        val newHighLow = mutableListOf<HighLow>()
        for (i in 0..data.length())
        {
            val embeder = getEmbedder(highLows[i]) // LSB Embedding the information bit
            newHighLow.add(embeder.embedBit(data[i]))
            lastI = i + 1
        }
        newHighLow.addAll(lastI,
                          highLows.subList(lastI,
                                           highLows.size
                                          )
                         )
        return getRegion(newHighLow)
    }

    abstract fun getEmbedder(highLow: HighLow): Embedder
    abstract fun getRegion(highLows: List<HighLow>): ARegion
    fun sortPairs(): ARegion
    {
        val highLows = mutableListOf<HighLow>()
        this.highLows.forEach {
            if (getCond(it))
                highLows.add(it)
        }
        return getRegion(highLows)
    }

    abstract fun getCond(it: HighLow): Boolean

    fun isInRange(highLow: HighLow): Boolean {
        val Rd = Integer.min(2 * (255 - highLow.low),
                             2 * highLow.low + 1
                            )
        var toCompare = getInRegionValue(highLow.high.toDouble(),1) // Information bit is 1
        if (!(toCompare >= 0 && toCompare <= Rd)) // Check if is in range when information bit is 1
            return false
        toCompare = getInRegionValue(highLow.high.toDouble(),0) // Information Bit is 0
        if (!(toCompare >= 0 && toCompare <= Rd))
            return false
        return true
    }

    abstract fun getInRegionValue(highValue: Double, bit: Int): Double

    fun extractData(): BitSet
    {
        val extractedBitSet = BitSet(highLows.size)
        highLows.forEachIndexed { index, highlow ->
            val extractedBit = highlow.high.and(0x01)
            if (extractedBit == 0x01.toByte())
                extractedBitSet.set(index)
            else
                extractedBitSet.clear(index)
        }
        return extractedBitSet
    }
}