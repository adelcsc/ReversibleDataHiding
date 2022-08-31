package dha

import java.util.BitSet
import java.util.stream.IntStream

class Payload(private val data: String)
{
    val bits = BitSet.valueOf(data.toByteArray())
}
