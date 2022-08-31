package dha

class HighLow(
    val high: Byte,
    val low: Byte,
    private val pxlPair: PixelPair = PixelPair(Pixel(0,
                                                     0,
                                                     0
                                                    ),
                                               Pixel(0,
                                                     0,
                                                     0
                                                    )
                                              )
             )
{
    constructor(high: Int, low: Int) : this(high.toByte(),
                                            low.toByte()
                                           )

    fun toPixelPair(): PixelPair
    {
        return pxlPair
    }

    override fun equals(other: Any?): Boolean
    {
        when (other)
        {
            is HighLow ->
            {
                if (other.high == high && other.low == low)
                    return true
            }

            is PixelPair ->
            {
                val hL = other.toHighLow()
                if (hL.high == high && hL.low == low)
                    return true
            }
        }
        return false
    }
}