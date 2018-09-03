package xinyiyun.chenfei.com.baselibrary.utils

import android.content.Context
import com.google.gson.Gson
import xinyiyun.chenfei.com.baselibrary.BuildConfig
import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by YangQiang on 2017/11/13.
 */
class Preference<T : Any>(val value: T) : ReadWriteProperty<Any, T> {
    val prefs by lazy { BaseApplication.application?.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE) }
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return findPreference(property.name, value)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        putPreference(property.name, value)
    }


    private inline fun findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> {
                try {
                    Gson().fromJson<T>(getString(name, ""), default.javaClass)
                } catch (e: Exception) {
                    default
                }
            }
        }
        res as T
    }


    private fun <A> putPreference(name: String, value: A) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> putString(name, Gson().toJson(value))
        }.apply()

    }

    /**
     * 删除全部数据
     */
     public  fun clearPreference() {
        prefs.edit().clear().commit()
    }

    /**
     * 根据key删除存储数据
     */
    fun clearPreference(key: String) {
        prefs.edit().remove(key).commit()
    }
}