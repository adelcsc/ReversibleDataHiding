package dha

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class HistogramTest
{

    @Test
    fun getDelta()
    {
        val payload = Payload("Adel Benhamida")
        val exp0 = List(80) {
            HighLow(0,
                    24
                   )
        }
        val exp1 = List(38) {
            HighLow(1,
                    24
                   )
        }
        val exp2 = List(8) {
            HighLow(2,
                    24
                   )
        }

        val others = List(251) {
            HighLow(-120,
                    100
                   )
        }
        val combined = mutableListOf<HighLow>()
        combined.addAll(others)
        combined.addAll(exp0)
        combined.addAll(exp1)
        combined.addAll(exp2)
        val expand = Expandable(combined)
        val histogram = Histogram(payload.bits.length(),
                                  expand
                                 )

        assertEquals(histogram.getDelta(),
                     1
                    ) // getDelta Function test

        // Histogram shift Test
        val newHistogram = histogram.sortEe()
        assertEquals(newHistogram.expandables.highLows.filter { it.high == (-122).toByte() }.size,
                     251
                    )
        assertEquals(newHistogram.expandables.highLows.filter { it.high == 4.toByte() }.size,
                     8
                    )
        assertEquals(newHistogram.expandables.highLows.filter { it.high == 0.toByte() }.size,
                     80
                    )
        assertEquals(newHistogram.expandables.highLows.filter { it.high == 1.toByte() }.size,
                     38
                    )


        // Histogram restore shift TEST
        val restoredHistogram = newHistogram.restore(1)
        assertEquals(restoredHistogram.expandables.highLows.filter { it.high == (-120).toByte() }.size,
                     251
                    )
        assertEquals(restoredHistogram.expandables.highLows.filter { it.high == 2.toByte() }.size,
                     8
                    )
        assertEquals(restoredHistogram.expandables.highLows.filter { it.high == 0.toByte() }.size,
                     80
                    )
        assertEquals(restoredHistogram.expandables.highLows.filter { it.high == 1.toByte() }.size,
                     38
                    )
    }
}