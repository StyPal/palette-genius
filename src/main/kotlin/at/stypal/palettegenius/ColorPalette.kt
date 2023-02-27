package at.stypal.palettegenius

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


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
        for (pixel in pixels){
            for (pixelInList in pixelList){
                if (pixel.getHexCode() == pixelInList.getHexCode()){
                    contains = true
                }
            }
            if (!contains) pixelList.add(pixel)
            contains = false
        }
    }

    private fun sortPalette(){
        Collections.sort(pixelList, comparator);
    }

    override fun toString(): String {
        val string: StringBuilder = StringBuilder("")
        for (color in pixelList){
            string.append(color.getHexCode()).append("\n")
        }
        return string.toString()
    }
}