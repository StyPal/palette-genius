package at.stypal.palettegenius

class Images {
    private val images: HashSet<Image> = HashSet()
    companion object{
        val colorPalette: ColorPalette = ColorPalette()
    }

    fun addImage(filePath: String){
        val image = Image(filePath)
        images.add(image)
        updateColorPalette(image)
    }

    private fun updateColorPalette(image: Image) {
        colorPalette.addColors(image.getPixels())
    }
}