package dha

import java.nio.ByteBuffer
import java.util.BitSet

class AuxilaryInformation(
    val header: Header,
    val compressedOVmap: BitSet
                         )
{
    val bits: BitSet = BitSet.valueOf(ByteBuffer.allocate(header.bits.size() / 8 + compressedOVmap.size() / 8)
                                          .put(header.bits.toByteArray()).put(compressedOVmap.toByteArray())
                                     )
}