package dha

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*
import kotlin.test.assertContentEquals

internal class ChangableTest
{

    @Test
    fun embedData()
    {
        val location = Changable(List(200) {
            HighLow(-5,
                    15
                   )
        })
        val payload = "AdelBenhamida"
        val newLocation = location.embedData(BitSet.valueOf(payload.toByteArray()))
        val extPayload : BitSet = newLocation.extractData()
        assertEquals(payload,String(extPayload.toByteArray().slice(IntRange(0,12)).toByteArray()))
    }


    @Test
    fun sortPairs()
    {
        // All are changeable
        var highLow = listOf(HighLow(120,
                                     70
                                    ),
                             HighLow(120,
                                     70
                                    ),
                             HighLow(120,
                                     70
                                    )
                            )
        var chang = Changable(highLow)
        var newChang = chang.sortPairs()
        assertContentEquals(highLow,
                            newChang.highLows
                           )

        // All are not changeable
        highLow = listOf(HighLow(180,
                                 21
                                ),
                         HighLow(175,
                                 180
                                )
                        )
        chang = Changable(highLow)
        newChang = chang.sortPairs()
        assertEquals(0,
                     newChang.highLows.size
                    )

        // Mixed of changeable and not changeable
        highLow = listOf(HighLow(180,// not Changeable
                                 21
                                ),
                         HighLow(120,// Changeable
                                 70
                                ),
                         HighLow(175,// not Changeable
                                 180
                                ),
                         HighLow(120,// Changeable
                                 70
                                )
                        )
        chang = Changable(highLow)
        newChang = chang.sortPairs()
        assert(newChang.highLows.equals(listOf(HighLow(120,// Changeable
                                                       70
                                                      ),
                                               HighLow(120,// Changeable
                                                       70
                                                      )
                                              )
                                       )
              )
    }
}