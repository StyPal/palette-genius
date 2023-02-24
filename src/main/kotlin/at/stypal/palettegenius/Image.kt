package at.stypal.palettegenius

import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class Image(filePath: String) {
    private val pixels: ArrayList<Pixel> = ArrayList()
    private lateinit var currentImage: BufferedImage

    init {
        try {
            val file = File(filePath)
            currentImage = ImageIO.read(file)
            readPixels()
        } catch (iox: IOException) {
            iox.printStackTrace()
        }
    }

    private fun readPixels() {
        val width = currentImage.width
        val height = currentImage.height
        for (y in 0 until height) {
            for (x in 0 until width) {
                val argb = currentImage.getRGB(x, y)

                val alpha = (argb shr 24) and 0xff
                val red = (argb shr 16) and 0xff
                val green = (argb shr 8) and 0xff
                val blue = argb and 0xff

                // Create a new Pixel object with the extracted components
                val onePixel = Pixel(red, green, blue, alpha)

                // Add the new pixel to the
                addPixel(onePixel)
            }
        }
    }

    fun addPixel(pixel: Pixel) {
        if (!(pixels.contains(pixel))) {
            this.pixels.add(pixel)
        }
    }
}