package at.stypal.palettegenius

import java.util.InputMismatchException
import java.util.Scanner

var images: Images = Images()

fun main() {
    val fileCount = getFileCount()
    getFilePaths(fileCount)
    getResult()
}

fun getResult() {
    val scanner = Scanner(System.`in`)
    println("How do you want to get your result?")
    println("   1. Here in the CLI [CLI]")
    println("   2. As generated PNG [PNG]")
    when (scanner.nextLine()) {
        "1", "CLI" -> getCLI()
        "2", "PNG" -> genPNG()
        else -> println("Only numbers 1 and 2 are valid!")
    }
}

fun genPNG() {
    println("Type in your path with the '.png' file extension: ")
    val scanner = Scanner(System.`in`)
    val filePath = scanner.nextLine()
    println(filePath.substring(filePath.length-4))
    when(filePath.substring(filePath.length-4)){
        ".png" ->  images.genPNG(filePath)
    }

}

fun getCLI() {
    println(images.getCLOutput())
}

fun getFilePaths(fileCount: Int) {
    println("Type in your paths to the images:")
    val scanner = Scanner(System.`in`)
    for (i in 0 until fileCount) {
        var fileName = scanner.nextLine()
        if (fileName[0] == '"' && fileName[fileName.length-1] == '"'){
            fileName = fileName.subSequence(1,fileName.length-1).toString()
        }
        images.addImage(fileName)
    }
}

fun getFileCount(): Int {
    var input: Int? = null
    print("How many files out of do you want to make your palette?: ")
    val scanner = Scanner(System.`in`)
    while (input == null) {
        try {
            val temp = scanner.nextInt()
            if (temp >= 1) {
                input = temp
            } else {
                throw InputMismatchException("Please type in a whole number above 0")
            }
        } catch (ex: InputMismatchException) {
            println("Please type in a whole number above 0")
            scanner.nextLine() // Discard invalid input
        }
    }
    return input
}
