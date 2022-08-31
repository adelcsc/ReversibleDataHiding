package dha

import java.util.*

class Neither(highLows: List<HighLow>) : ARegion(highLows)
{
    override fun getEmbedder(highLow: HighLow): Embedder
    {
        //Doesn't execute
        return LSBEmbed(highLow)
    }

    override fun getCond(it: HighLow): Boolean
    {
        val changeableChecker = Changable(emptyList())
        return !changeableChecker.isInRange(it)
    }

    override fun getRegion(highLows: List<HighLow>): Neither
    {
        return Neither(highLows)
    }

    override fun getInRegionValue(highValue: Double, bit: Int): Double
    {
        return 0.toDouble()
    }

}