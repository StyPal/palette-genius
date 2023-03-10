package at.stypal.palettegenius

import java.util.InputMismatchException

class Pixel(argb: Int) {
    private var argb: Int = 0
    private var red: Int = 0
    private var green: Int = 0
    private var blue: Int = 0
    private var alpha: Int = 0
    private var inRange: Boolean = false

    init {
        try{
            val alpha = (argb shr 24) and 0xff
            val red = (argb shr 16) and 0xff
            val green = (argb shr 8) and 0xff
            val blue = argb and 0xff

            inRange = valuesInRange(red, green, blue, alpha)
            if (!inRange) {
                throw InputMismatchException()
            }

            this.argb = argb
            this.alpha = alpha
            this.red = red
            this.green = green
            this.blue = blue
        }catch (imx: InputMismatchException){
            imx.printStackTrace()
        }
    }

    fun getHexCode(): String{
        var redStr = Integer.toHexString(this.red)
        var greenStr = Integer.toHexString(this.green)
        var blueStr = Integer.toHexString(this.blue)
        var alphaStr = ""

        // Add leading zeros to ensure a two-digit hexadecimal value
        if (redStr.length == 1) redStr = "0$redStr"
        if (greenStr.length == 1) greenStr = "0$greenStr"
        if (blueStr.length == 1) blueStr = "0$blueStr"
        if (alpha != 255) alphaStr = String.format(" - Alpha: %02x", alpha)
        // returns Color as HexCode (e.g. #FF00FF)
        return "#$redStr$greenStr$blueStr$alphaStr"
    }

    private fun valuesInRange(red: Int, green: Int, blue: Int, alpha: Int): Boolean {
        return (red in 0..255) && (green in 0..255) && (blue in 0..255) && (alpha in 1..255)
    }

    fun getARGB(): Int{
        return argb
    }
}