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

fun String.repoUrlValid(pattern : String) : Boolean{
    val str = this
    if(str.equals("")) return true
    val exclude_path = arrayOf(
        "enterprise",
        "features",
        "topics",
        "collections",
        "trending",
        "events",
        "marketplace",
        "pricing",
        "nonprofit",
        "customer-stories",
        "security",
        "login",
        "join"
    )
    for (word in exclude_path) {
        if(str.matches("""[\w|\W]+(${word})+[\w|\W]*""".toRegex())) return false
    }
    return str.matches(pattern.toRegex())
}
