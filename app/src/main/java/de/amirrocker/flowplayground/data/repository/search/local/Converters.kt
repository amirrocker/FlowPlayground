package de.amirrocker.flowplayground.data.repository.search.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.amirrocker.flowplayground.data.Meaning

//@ProvidedTypeConverter
//class Converters(
//    private val jsonParser: JsonParser
//) {
//    @TypeConverter
//    fun fromMeaningsJson(json:String): List<Meaning> =
//        jsonParser.fromJson(
//            json = json,
//            object : TypeToken<ArrayList<Meaning>>(){}.type
//        ) ?: emptyList()
//
//    @TypeConverter
//    fun toMeaningsJson(meanings:List<Meaning>): String =
//        jsonParser.toJson(
//            instance = meanings,
//            object : TypeToken<ArrayList<Meaning>>(){}.type
//        ) ?: "[]"
//}

class Converters {

    private val gson = Gson()

    // Training json converters - database saves and reads json.
    @TypeConverter
    fun fromMeaningsJson(list:List<Meaning>?):String =
        list?.let { gson.toJson(it) } ?: ""

    @TypeConverter
    fun toMeaningsJson(jsonList:String):List<Meaning> {
        val listType = object : TypeToken<List<Meaning>>() {}.type
        return gson.fromJson(jsonList, listType)
    }
}