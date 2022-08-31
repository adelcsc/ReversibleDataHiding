package dha

import java.awt.image.Raster

class RightOddCol(nextHandler: IPictureHandler?) : IPictureHandler(nextHandler)
{
    override fun neighborCol(j: Int): Int
    {
        return j
    }

    override fun neighborRow(i: Int): Int
    {
        return i+1
    }

    override fun skipCondition(i: Int, j: Int, width: Int, height: Int): Boolean
    {
        var cond=true
        if (width % 2 == 1 && i % 2 == 0 && j == width - 1)
            cond=false
        else
            return cond
        if (height%2==1)
            cond= i == height - 1
        else
            cond = false
        return cond
    }

}