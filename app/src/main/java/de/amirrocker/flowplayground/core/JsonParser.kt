package de.amirrocker.flowplayground.core

import com.google.gson.Gson
import java.lang.reflect.Type

interface JsonParser {
    fun <T> fromJson(json:String, type:Type):T
    fun <T> toJson(instance:T, type:Type):String
}

class GsonParser(
        private val gson: Gson = Gson()
) : JsonParser {

    override fun <T> fromJson(json: String, type: Type): T =
        gson.fromJson<T>(json, type)


    override fun <T> toJson(instance: T, type: Type): String =
        gson.toJson(instance, type)

}
