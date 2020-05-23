package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils.transliteration

data class Profile (
    val firstName : String,
    val lastName : String,
    val about : String,
    val repo : String,
    val rating : Int = 0,
    val respect : Int = 0
){
    val nickName : String
        get() {
            var result = "${transliteration(firstName.replace(" ".toRegex(), "_"))}_${transliteration(lastName.replace(" ".toRegex(), "_"))}"
            return result
        }

    val rank : String = "Junior Android Developer"

    fun toMap() : Map<String, Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repo" to repo,
        "rating" to rating,
        "respect" to respect
    )
}