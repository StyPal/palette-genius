package at.stypal.palettegenius

class Images {
    private val images: ArrayList<Image> = ArrayList()
    companion object{
        val colorPalette: ColorPalette = ColorPalette()
    }

    fun addImage(filePath: String){
        val image = Image(filePath)
        if (!images.contains(image)){
            images.add(image)
        }
        updateColorPalette(image)
    }

    private fun updateColorPalette(image: Image) {
        colorPalette.addColors(image.getPixels())
    }

    fun getCLOutput(): String{
        return colorPalette.toString()
    }
}