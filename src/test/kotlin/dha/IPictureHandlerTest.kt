package dha

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import javax.imageio.ImageIO

internal class IPictureHandlerTest
{
    @Test
    fun generalTest() {
        val image = ImageIO.read(File("C:\\Users\\po4A\\Desktop\\TRASH\\lena_odd.png"))
        val pictureHandler = LeftRect(RightOddCol(null))
        val sorted = pictureHandler.process(image.data)
    }
}