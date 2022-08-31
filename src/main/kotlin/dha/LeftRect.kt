package dha

class LeftRect(nextHandler: IPictureHandler?) : IPictureHandler(nextHandler)
{
    override fun neighborCol(j: Int): Int
    {
        return j+1
    }

    override fun neighborRow(i: Int): Int
    {
        return i
    }

    override fun skipCondition(i: Int, j: Int, width: Int, height: Int): Boolean
    {
        if (j%2==1)
            return true
        if (width%2==1)
            if  (j==width-1)
                return true
        return false
    }

}