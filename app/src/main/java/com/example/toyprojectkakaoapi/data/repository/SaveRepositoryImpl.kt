package com.example.toyprojectkakaoapi.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.toyprojectkakaoapi.data.repository.SaveRepositoryImpl.Key.KEY_FAVORITE
import com.example.toyprojectkakaoapi.domain.entity.SearchDocumentEntity
import com.example.toyprojectkakaoapi.domain.repository.SaveRepository
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class SaveRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : SaveRepository {
    private val pref = context.getSharedPreferences(KEY_FAVORITE, Context.MODE_PRIVATE)
    private val edit = pref.edit()
    private val gson = Gson()
    private val typeToken = object : TypeToken<ArrayList<SearchDocumentEntity>>() {}.type
    object Key {
        const val KEY_FAVORITE = "pref_favorite"
    }

    override suspend fun saveSearchEntity(item: SearchDocumentEntity) {
        if (pref.contains(KEY_FAVORITE)) {
            try {
                val json = pref.getString(KEY_FAVORITE, "")
                val savedData: ArrayList<SearchDocumentEntity> = gson.fromJson(json, typeToken)
                savedData.add(item)

                val newJson = gson.toJson(savedData)
                edit.putString(KEY_FAVORITE, newJson)
                edit.apply()

            } catch (e: JsonParseException) {
                e.printStackTrace()
            }
        } else {
            val newData: ArrayList<SearchDocumentEntity> = arrayListOf()
            newData.add(item)
            val newJson = gson.toJson(newData)
            edit.putString(KEY_FAVORITE, newJson)
            edit.apply()
        }
    }

    override suspend fun loadSearchEntity(): Flow<List<SearchDocumentEntity>> = callbackFlow {
        val json = pref.getString(KEY_FAVORITE, "")
        val savedData: List<SearchDocumentEntity> = gson.fromJson(json, typeToken)
        trySend(savedData)

        // 실시간 데이터 반영
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { prefs, key ->
            if (key == KEY_FAVORITE) {
                val updatedJson = prefs.getString(key, "")
                val updatedData : ArrayList<SearchDocumentEntity> = gson.fromJson(updatedJson, typeToken)
                trySend(updatedData).isSuccess
            }
        }
        pref.registerOnSharedPreferenceChangeListener(listener)
        awaitClose { pref.unregisterOnSharedPreferenceChangeListener(listener) }
    }

    override suspend fun deleteSearchEntity(item: SearchDocumentEntity) {
        if (pref.contains(KEY_FAVORITE)) {
            val json = pref.getString(KEY_FAVORITE, "")
            try {
                val savedData: ArrayList<SearchDocumentEntity> = gson.fromJson(json, typeToken)
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