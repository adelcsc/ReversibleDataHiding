package dha

import java.lang.Math.*
import java.util.*
import kotlin.experimental.and

class Changable(highLows: List<HighLow>) : ARegion(highLows)
{
    override fun getEmbedder(highLow: HighLow): LSBEmbed
    {
        return LSBEmbed(highLow)
    }

    override fun getCond(it: HighLow): Boolean
    {
        val expand = Expandable(emptyList())
        return isInRange(it)&&!expand.isInRange(it)
    }

    override fun getRegion(highLows: List<HighLow>): Changable
    {
        return Changable(highLows)
    }

    override fun getInRegionValue(highValue: Double, bit: Int): Double
    {
        return abs(2 * floor(highValue / 2) + 1)
    }
    fun saveLSBs() : BitSet
    {
        val lsbs = BitSet(highLows.size)
        highLows.forEachIndexed { index,it ->
            if(it.high.and(0x01.toByte())==1.toByte())
                lsbs.set(index)
            else
                lsbs.clear(index)
        }
        return lsbs
    }
}