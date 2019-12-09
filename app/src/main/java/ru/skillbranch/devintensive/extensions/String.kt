package ru.skillbranch.devintensive.extensions

fun String.truncate(quantity: Int = 16):String{
    var str = this.trim()
    if(str.length <= quantity) return str

    var result = str.substring(0, quantity).trim() +"..."
    return result
}

fun String.stripHtml(): String{
    var str = this

    var isTag = false
    var result = ""

    str.forEachIndexed{
        index, char ->
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