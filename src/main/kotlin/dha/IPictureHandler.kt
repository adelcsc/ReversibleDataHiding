package dha

import java.awt.image.Raster

abstract class IPictureHandler(val nextHandler: IPictureHandler?)
{
    val sorted = mutableListOf<PixelPair>()
    fun process(img : Raster) : List<PixelPair> {
        for (i in 0 until img.height)
            for(j in 0 until img.width)
                if(skipCondition(i,j,img.width,img.height))
                    continue
                else
                    sorted.add(
                            PixelPair(
                                    Pixel(
                                            getGrayAt(i,j,img),
                                            i*img.width+j,
                                            img.width
                                         ),
                                    Pixel(
                                            getGrayAt(neighborRow(i),neighborCol(j),img),
                                            neighborRow(i) * img.width + neighborCol(j),
                                            img.width
                                         )
                                     )
                              )
        nextHandler?.let {
            sorted.addAll(it.process(img))
        }
        return sorted
    }

    abstract fun neighborCol(j: Int): Int

    abstract fun neighborRow(i: Int): Int

    abstract fun skipCondition(i: Int, j: Int, width: Int, height: Int): Boolean

    private fun getGrayAt(i: Int,j:Int,img: Raster) : Byte{
        val gray = img.getSample(j,i,0)+img.getSample(j,i,1)+img.getSample(j,i,2)
        return (gray/3).toByte()
    }
}