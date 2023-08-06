package com.example.composeapp

import android.os.Bundle
import androidx.navigation.NavType
import com.example.composeapp.circularprogressbar.CircularProgressBarItem
import com.example.composeapp.expandablecard.ExpandableCardItem
import com.example.composeapp.imagecard.ImageCardItem
import com.google.gson.Gson

abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) =
        bundle.putString(key, value.getJsonParse())
}

class ExpandableCardItemNavArgType : JsonNavType<ExpandableCardItem>() {

    override fun fromJsonParse(value: String): ExpandableCardItem =
        Gson().fromJson(value, ExpandableCardItem::class.java)

    override fun ExpandableCardItem.getJsonParse(): String = Gson().toJson(this)
}

class ImageCardItemNavArgType : JsonNavType<ImageCardItem>() {

    override fun fromJsonParse(value: String): ImageCardItem = Gson().fromJson(value, ImageCardItem::class.java)

    override fun ImageCardItem.getJsonParse(): String = Gson().toJson(this)
}

class CircularProgressBarItemNavArgType : JsonNavType<CircularProgressBarItem>() {

    override fun fromJsonParse(value: String): CircularProgressBarItem =
        Gson().fromJson(value, CircularProgressBarItem::class.java)

    override fun CircularProgressBarItem.getJsonParse(): String = Gson().toJson(this)
}