package dha

import java.lang.Exception

class Histogram(val sizeToEmbed: Int, val expandables: Expandable)
{

    val deltaValue : Int
    fun sortEe(): Expandable
    {
        val shifted= expandables.highLows.filter {
            when{
                it.high > deltaValue -> false
                it.high < -(deltaValue + 1) -> false
                else -> true
            }
        }
        return Expandable(shifted)
    }
    fun getDelta(): Int
    {
        var delta = 0
        while (expandables.highLows.filter { it.high.toInt()<=delta && it.high.toInt()>= -(delta-1) }.size < sizeToEmbed) //TODO("add auxilary information")
            if (delta==255)
                throw Exception("Payload size is too big ")
            else
            delta++
        return delta
    }
    fun restore(delta : Int) : Histogram {
        val restored = expandables.highLows.map {
            when {
                it.high>2*delta+1 -> HighLow(it.high-delta-1,it.low.toInt())
                it.high<-(2*delta+2) -> HighLow(it.high+delta+1,it.low.toInt())
                else -> it
            }
        }
        return Histogram(sizeToEmbed,
                         Expandable(restored)
                        )
    }

    fun sortEs(): Changable
    {
        return Changable(expandables.highLows.filter {
            when
            {
                it.high > deltaValue -> true
                it.high < -(deltaValue + 1) -> true
                else -> false
            }
        }.map {
            when
            {
                it.high > deltaValue -> HighLow(it.high + deltaValue + 1,
                                                it.low.toInt()
                                               )

                it.high < -(deltaValue + 1) -> HighLow(it.high - deltaValue - 1,
                                                       it.low.toInt()
                                                      )

                else -> it
            }
        })
    }

    init
    {
        deltaValue = getDelta()
    }

}