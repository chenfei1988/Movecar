/**
 *
 */
package xinyiyun.chenfei.com.baselibrary.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication;



public class PreferencesHelper {

    public static final String P_NAME = "shareference_name";

    private static SharedPreferences sp;
    private static PreferencesHelper ph;

    private PreferencesHelper() {

    }

    public static PreferencesHelper getPerferences(Context a) {
        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        return ph;
    }

    public static PreferencesHelper getPerferences() {
        return ph;
    }

    public static void setInfo(String name, String data) {
        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        SharedPreferences.Editor e = sp.edit().putString(name, data);
        e.commit();
    }

    public static void setInfo(String name, int data) {
        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        SharedPreferences.Editor e = sp.edit().putInt(name, data);
        e.commit();
    }

    public static void setInfo(String name, boolean data) {
        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        SharedPreferences.Editor e = sp.edit().putBoolean(name, data);
        e.commit();
    }


    public static int getIntData(String name) {
        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        return sp.getInt(name, 0);
    }

    public static String getStringData(String name) {
        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        return sp.getString(name, "");
    }

    /**
     * @param name
     * @param defaultStr
     * @return 自定义默认值的String
     */
    public static String getStringData(String name, String defaultStr) {
        if (ph == null) {
            ph = new PreferencesHelper();
            sp =BaseApplication.application.getSharedPreferences(P_NAME, 0);
        } else {
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        if (sp == null) {
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }

        return sp.getString(name, defaultStr);
    }

    public static boolean getBooleanData(String name) {
        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        return sp.getBoolean(name, false);
    }

    public static void setInfo(String name, long data) {
        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        SharedPreferences.Editor e = sp.edit().putLong(name, data);
        e.commit();
    }

    public static long getLongData(String name) {
        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        return sp.getLong(name, 0);
    }

    /**
     * 存对象
     *
     * @param key
     * @param object
     */
    public static void putObject(String key, Object object) {

        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }

        if (object == null) {
            sp.edit().putString(key, "").commit();
        } else {
            String value = JSONObject.toJSONString(object);
            sp.edit().putString(key, value).commit();
        }
    }

    /**
     * 取对象
     *
     * @param key
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getObject(String key, Class<T> c) {

        if (ph == null) {
            ph = new PreferencesHelper();
            sp = BaseApplication.application.getSharedPreferences(P_NAME, 0);
        }
        String value = sp.getString(key, "");
        if (TextUtils.isEmpty(value))
            return null;
        T t = JSONObject.parseObject(value, c);
        return t;
    }

    /**
     * 清除缓存数据
     */
    public static void clearData() {
        sp.edit().clear().commit();
    }
}
