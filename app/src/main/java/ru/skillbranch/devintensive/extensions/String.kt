package ru.skillbranch.devintensive.extensions

fun String.truncate(quantity: Int = 16):String{
    var str = this.trim()
    if(str.length <= quantity) return str

    var result = str.substring(0, quantity).trim() +"..."
    return result
}

fun String.stripHtml(): String{
    val str = this

    var isTag = false
    var result = ""

    str.forEach{
        char ->
            when(char){
                '<' -> isTag = true
                '>' -> isTag = false
                else -> {
                    if(!isTag) result += char
                }
            }
    }
    result = result.replace("&[a-z]+;".toRegex(), "")
    result = result.replace("&#[0-9]+;".toRegex(), "")
    result = result.replace("[ \\f\\t]+".toRegex(), " ")
    return result
}

//fun String.replaceChar(oldChar: Char, newChar : Char) : String{
//    val str = this
//
//    val r1 = str.toCharArray().let {
//        for (i in 0..it.lastIndex - 1) {
//            if (it[i] == oldChar) it[i] = newChar
//        }
//    }
//    return ""
//}
