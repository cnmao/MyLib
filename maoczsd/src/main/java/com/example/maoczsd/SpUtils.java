package com.example.maoczsd;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class SPUtils {

    private static Map<String, SPUtils> sSPMap = new HashMap<>();
    private SharedPreferences sp;


    public static SPUtils getInstance() {
        return getInstance("");
    }


    public static SPUtils getInstance(String spName) {
        if (isSpace(spName)) spName = "spUtils";
        SPUtils sp = sSPMap.get(spName);
        if (sp == null) {
            sp = new SPUtils(spName);
            sSPMap.put(spName, sp);
        }
        return sp;
    }

    private SPUtils(String spName) {
        sp = Utils.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public void put(@NonNull String key, @NonNull String value) {
        sp.edit().putString(key, value).apply();
    }

    private static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}