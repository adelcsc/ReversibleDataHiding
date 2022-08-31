package dha

import org.xerial.snappy.Snappy
import java.io.File
import java.nio.ByteBuffer
import java.util.*
import javax.imageio.ImageIO

class DifferenceExpansion(path: String)
{
    private val list: List<HighLow>
    private val changable : Changable
    private val expandable : Expandable
    private val neither : Neither
    init
    {
        val image = ImageIO.read(File(path))
        val pictureHandler = LeftRect(RightOddCol(null))
        list = pictureHandler.process(image.data).map { it.toHighLow() }
        changable = Changable(list)
        expandable = Expandable(list)
        neither = Neither(list)
    }

    fun embedPayload(payload: Payload)
    {
        val ovrMap=expandable.getOverFlowMap(list.size)
        val compOvrMap = Snappy.compress(ovrMap.toByteArray())
        val histogram = Histogram(payload.bits.length() + compOvrMap.size * 8+Header.SIZE_IN_BITS,
                                  expandable
                                 )

        val regionEe = Expandable(histogram.sortEe().highLows)
        val empty = mutableListOf<HighLow>()
        empty.addAll(changable.highLows)
        empty.addAll(histogram.sortEs().highLows)
        val regionEsC = Changable(empty)
        val lsbs = regionEsC.saveLSBs()
        val header = Header(compOvrMap.size*8,histogram.getDelta().toByte(),payload.bits.length())
        val auxilaryInformation = AuxilaryInformation(header,
                                                      BitSet.valueOf(compOvrMap))
        val expansionEmbedded = BitSet.valueOf(ByteBuffer
                                                   .allocate(
                                                           (auxilaryInformation.bits.length() + 1) / 8+(lsbs.length()+1)/8
                                                            )
                                                   .put(auxilaryInformation.bits.toByteArray())
                                                   .put(lsbs.toByteArray())
                                              )
        regionEe.embedData(expansionEmbedded)
        regionEsC.embedData(lsbs)
    }

    fun restoreOriginal()
    {

    }

    fun showImage()
    {

    }
}