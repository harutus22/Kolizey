package com.example.kolizey.utils

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.kolizey.model.User
import com.google.gson.Gson

fun setSharedBoolean(text: String, isPlaying: Boolean, context: Context) {
    val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
    editor.putBoolean(text, isPlaying)
    editor.apply()
}

fun setString(text: String, value: String, context: Context) {
    val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
    editor.putString(text, value)
    editor.apply()
}

fun setInt(text: String, value: Int, context: Context) {
    val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
    editor.putInt(text, value)
    editor.apply()
}

fun getSharedBoolean(text: String, context: Context) = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(text, true)
fun getSharedString(context: Context, title: String) =
    PreferenceManager.getDefaultSharedPreferences(context).getString(title, "")

fun getSharedInt(text: String, context: Context) = PreferenceManager.getDefaultSharedPreferences(context).getInt(text, -1)

//fun getUser(context: Context): User {
//    val gson = Gson()
//    val a = getSharedString(context, USER)
//    return gson.fromJson(a, User::class.java)
//}
//
//fun setUser(context: Context, model: User) {
//    val gson = Gson();
//    val json = gson.toJson(model);
//    setString(USER, json, context)
//}
