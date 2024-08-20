package com.example.toyprojectkakaoapi.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.toyprojectkakaoapi.data.repository.SaveRepositoryImpl.Key.KEY_FAVORITE
import com.example.toyprojectkakaoapi.domain.entity.SearchEntity
import com.example.toyprojectkakaoapi.domain.entity.SearchEntityList
import com.example.toyprojectkakaoapi.domain.repository.SaveRepository
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : SaveRepository {
    private val pref = context.getSharedPreferences(KEY_FAVORITE, Context.MODE_PRIVATE)
    private val edit = pref.edit()
    private val gson = Gson()
    private val typeToken = object : TypeToken<ArrayList<SearchEntity>>() {}.type
    object Key {
        const val KEY_FAVORITE = "pref_favorite"
    }

    override suspend fun saveSearchEntity(item: SearchEntity) {
        if (pref.contains(KEY_FAVORITE)) {
            try {
                val json = pref.getString(KEY_FAVORITE, "")
                val savedData: ArrayList<SearchEntity> = gson.fromJson(json, typeToken)
                savedData.add(item)

                val newJson = gson.toJson(savedData)
                edit.putString(KEY_FAVORITE, newJson)
                edit.apply()

            } catch (e: JsonParseException) {
                e.printStackTrace()
            }
        } else {
            val newData: ArrayList<SearchEntity> = arrayListOf()
            newData.add(item)
            val newJson = gson.toJson(newData)
            edit.putString(KEY_FAVORITE, newJson)
            edit.apply()
        }
    }

    override suspend fun loadSearchEntity(): Flow<SearchEntityList> = flow {
        if (pref.contains(KEY_FAVORITE)) {
            val json = pref.getString(KEY_FAVORITE, "")
            val savedData: ArrayList<SearchEntity> = gson.fromJson(json, typeToken)
            emit(SearchEntityList(savedData))


        } else {
            emit(SearchEntityList(listOf()))
        }
    }

    override suspend fun deleteSearchEntity(item: SearchEntity) {
        if (pref.contains(KEY_FAVORITE)) {
            val json = pref.getString(KEY_FAVORITE, "")
            try {
                val savedData: ArrayList<SearchEntity> = gson.fromJson(json, typeToken)
                savedData.remove(item)
                val newJson = gson.toJson(savedData)
                edit.putString(KEY_FAVORITE, newJson)
                edit.apply()

            } catch (e: JsonParseException) {
                e.printStackTrace()
            }
        }
    }

}