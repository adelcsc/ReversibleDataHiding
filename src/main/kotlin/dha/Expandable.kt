package dha

import java.lang.Math.abs
import java.util.*

class Expandable(highLows: List<HighLow>) : ARegion(highLows)
{
    override fun getEmbedder(highLow: HighLow): Embedder
    {
        return ExpandEmbed(highLow)
    }

    override fun getRegion(highLows: List<HighLow>): Expandable
    {
        return Expandable(highLows)
    }

    override fun getCond(it: HighLow): Boolean
    {
        return isInRange(it)
    }

    override fun getInRegionValue(highValue: Double, bit: Int): Double
    {
        return abs(2*highValue+bit)
    }
    fun getOverFlowMap(total : Int) : BitSet
    {
        val ovrMap = BitSet(total)
        ovrMap.clear()
        highLows.forEach {
            val expandableIndex = it.toPixelPair().a.index/2
            ovrMap.set(expandableIndex)
        }
        return ovrMap
    }
}