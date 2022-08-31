package dha

abstract class Embedder(val highLow: HighLow)
{
    abstract fun embedBit(bit: Boolean) : HighLow
    abstract fun restore(bit : Boolean=false) : HighLow
}