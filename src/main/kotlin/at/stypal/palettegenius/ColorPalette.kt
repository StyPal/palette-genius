package at.stypal.palettegenius

import java.awt.image.BufferedImage
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.math.ln
import kotlin.math.pow


class ColorPalette {
    private val pixelList: ArrayList<Pixel> = ArrayList()

    private var comparator = Comparator<Pixel> { p1, p2 ->
        val r1: Int = p1.getRed()
        val g1: Int = p1.getGreen()
        val b1: Int = p1.getBlue()
        val r2: Int = p2.getRed()
        val g2: Int = p2.getGreen()
        val b2: Int = p2.getBlue()
        if (r1 != r2) {
            r1.compareTo(r2)
        } else if (g1 != g2) {
            g1.compareTo(g2)
        } else {
            b1.compareTo(b2)
        }
    }

    fun addColors(pixels: Collection<Pixel>) {
        var contains = false
        for (pixel in pixels) {
            for (pixelInList in pixelList) {
                if (pixel.getHexCode() == pixelInList.getHexCode()) {
                    contains = true
                }
            }
            if (!contains) pixelList.add(pixel)
            contains = false
        }
    }

    private fun sortPalette() {
        Collections.sort(pixelList, comparator)
    }

    override fun toString(): String {
        val string: StringBuilder = StringBuilder("")
        for (color in pixelList) {
            string.append(color.getHexCode()).append("\n")
        }
        return string.toString()
    }

    fun genPNG(filePath: String) {
        //For getting an image with a 1:1 ratio and a power of 2 length/width
        val x = ln(20.0).div(2 * ln(2.0))
        var size = 2.0.pow((x.toInt() + 1).toDouble()).toInt()


        val bufferedImage: BufferedImage = BufferedImage(size, size, 2)
        var count = 0
        for (y in 0 until size) {
            for (x in 0 until size) {
                if (count+1 < pixelList.size){
                    //bufferedImage.setRGB(x,y,pixelList.get(count).getIntValue())
                    count+=1
                }else{
                    break
                }
            }
        }
    }
}