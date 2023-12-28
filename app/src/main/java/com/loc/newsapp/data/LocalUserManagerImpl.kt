package com.loc.newsapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.util.Constant
import com.loc.newsapp.util.Constant.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//create a datastore which store the values locally in the system.
//you know shared preference to store the values in the key value pair
class LocalUserManagerImpl(
    private val context: Context
):LocalUserManager {
    override suspend fun saveAppEntry() {
        //to save the data it returns the value in the boolean values.
        context.datastore.edit { settings ->
            settings[PreferenceKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        //return the particular key value pair. for using map.
        return context.datastore.data.map { preferences ->
            //if it is null then return false means no value for that.
            preferences[PreferenceKeys.APP_ENTRY] ?:false
        }
    }
}

//first up all create an instance of the datastore and to get that just create an extension value
private val Context.datastore:DataStore<Preferences> by preferencesDataStore(name = USER_SETTING)

//lets also create the preferences key for storing values.
private object PreferenceKeys{
    val APP_ENTRY = booleanPreferencesKey(name = Constant.APP_ENTRY)
}