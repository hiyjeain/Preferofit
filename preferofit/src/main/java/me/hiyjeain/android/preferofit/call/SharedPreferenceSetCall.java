package me.hiyjeain.android.preferofit.call;

import android.content.SharedPreferences;

/**
 * Created by garrett on 4/1/16.
 */
class SharedPreferenceSetCall extends SharedPreferenceCall{

    SharedPreferenceSetCall(String key, Class returnType, Class argumentType, SharedPreferences sharedPreference) {
        super(key, returnType, argumentType, sharedPreference);
    }

    @Override
    String invokeString(String value) {
        sharedPreference.edit().putString(key, value).apply();
        return value;
    }

    @Override
    Integer invokeInteger(Integer value) {
        sharedPreference.edit().putInt(key, value).apply();
        return value;
    }

    @Override
    Long invokeLong(Long value) {
        sharedPreference.edit().putLong(key, value).apply();
        return value;
    }

    @Override
    Float invokeFloat(Float value) {
        sharedPreference.edit().putFloat(key, value).apply();
        return value;
    }

    @Override
    Boolean invokeBoolean(Boolean value) {
        sharedPreference.edit().putBoolean(key, value).apply();
        return value;
    }
}
