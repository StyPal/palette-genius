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

                if(((argb shr 24) and 0xff) != 0) {
                    // Create a new Pixel object with the extracted components
                    val onePixel = Pixel(argb)
                    // Add the new pixel to the
                    addPixel(onePixel)
                }
            }
        }
    }

    private fun addPixel(pixel: Pixel) {
        if(pixels.contains(pixel)) return
        this.pixels.add(pixel)
    }

    fun getPixels(): ArrayList<Pixel> {
        return pixels
    }
}