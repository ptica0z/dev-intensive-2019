package ru.skillbranch.devintensive.utils

import android.annotation.SuppressLint

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?>{

        if(fullName.isNullOrBlank()) return null to null

        val parts: List<String>? = fullName.trim().split(" ")

        return parts?.getOrNull(0)?.trim() to parts?.getOrNull(1)?.trim()
    }

    @SuppressLint("DefaultLocale")
    fun toInitials(firstName:String?, lastName:String?): String?{
        if(firstName.isNullOrBlank() && lastName.isNullOrBlank()) return null

        val result = (firstName?.trim()?.substring(0,1)?.toUpperCase() ?: "") + (lastName?.trim()?.substring(0,1)?.toUpperCase() ?: "")
        return result
    }

    fun transliteration(payload:String, divider: String = " ") : String{
        var result: String = ""
        val str = payload.trim()
        for(i in 0..str.length-1 step 1){
            result += when(str[i]){
                'а' -> "a"
                'б' -> "b"
                'в' -> "v"
                'г' -> "g"
                'д' -> "d"
                'е' -> "e"
                'ё' -> "e"
                'ж' -> "zh"
                'з' -> "z"
                'и' -> "i"
                'й' -> "i"
                'к' -> "k"
                'л' -> "l"
                'м' -> "m"
                'н' -> "n"
                'о' -> "o"
                'п' -> "p"
                'р' -> "r"
                'с' -> "s"
                'т' -> "t"
                'у' -> "u"
                'ф' -> "f"
                'х' -> "h"
                'ц' -> "c"
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh'"
                'ъ' -> ""
                'ы' -> "i"
                'ь' -> ""
                'э' -> "e"
                'ю' -> "yu"
                'я' -> "ya"

                'А' -> "A"
                'Б' -> "B"
                'В' -> "V"
                'Г' -> "G"
                'Д' -> "D"
                'Е' -> "E"
                'Ё' -> "E"
                'Ж' -> "Zh"
                'З' -> "Z"
                'И' -> "I"
                'Й' -> "I"
                'К' -> "K"
                'Л' -> "L"
                'М' -> "M"
                'Н' -> "N"
                'О' -> "O"
                'П' -> "P"
                'Р' -> "R"
                'С' -> "S"
                'Т' -> "T"
                'У' -> "U"
                'Ф' -> "F"
                'Х' -> "H"
                'Ц' -> "C"
                'Ч' -> "Ch"
                'Ш' -> "Sh"
                'Щ' -> "Sh'"
                'Ъ' -> ""
                'Ы' -> "I"
                'Ь' -> ""
                'Э' -> "E"
                'Ю' -> "Yu"
                'Я' -> "Ya"
                else -> str[i]
            }
        }
        result = result.replace(" ", divider)
        return result
    }
}