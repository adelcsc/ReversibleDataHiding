package dha

import java.nio.ByteBuffer
import java.util.BitSet
import java.util.stream.IntStream

class Header(
    compressedOverFlowMapSize: Int,
    val delta: Byte,
    payloadSize: Int
            )
{
    val bits: BitSet
    init
    {
        bits = BitSet.valueOf(ByteBuffer
                                  .allocate(9)
                                  .putInt(compressedOverFlowMapSize)
                                  .put(delta)
                                  .putInt(payloadSize)
                             )
    }
    companion object {
        const val SIZE_IN_BITS = 32 + // 32 bits for the Size of Compressed OverFlow map
                                 8+ // 8 bits for delta value
                                 32 // 32 bits for Size of payload
    }
}