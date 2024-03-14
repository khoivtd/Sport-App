package com.sport.app.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.sport.app.feature_event.domain.models.Match

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMatchesJson(json: String): List<Match> {
        return jsonParser.fromJson<ArrayList<Match>>(
            json, object : TypeToken<ArrayList<Match>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMatchesJson(meanings: List<Match>): String {
        return jsonParser.toJson(
            meanings, object : TypeToken<ArrayList<Match>>() {}.type
        ) ?: "[]"
    }
}