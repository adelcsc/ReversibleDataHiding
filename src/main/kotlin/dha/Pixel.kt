package dha

class Pixel(
    val value: Byte,
    val index: Int,
    val width: Int
           )
{
    fun getRow(): Int
    {
        return index / width
    }

    fun getCol(): Int
    {
        return index % width
    }
}