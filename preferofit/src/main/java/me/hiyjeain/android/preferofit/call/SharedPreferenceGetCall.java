package me.hiyjeain.android.preferofit.call;

import android.content.SharedPreferences;

/**
 * Created by garrett on 4/1/16.
 */
 class SharedPreferenceGetCall extends SharedPreferenceCall {

    SharedPreferenceGetCall(String key, Class returnType, Class argumentType, SharedPreferences sharedPreference) {
        super(key, returnType, argumentType, sharedPreference);
    }

    @Override
    String invokeString(String value) {
        return sharedPreference.getString(key, value);
    }

    @Override
    Integer invokeInteger(Integer value) {
        return sharedPreference.getInt(key, value);
    }

    @Override
    Long invokeLong(Long value) {
        return sharedPreference.getLong(key, value);
    }

    @Override
    Float invokeFloat(Float value) {
        return sharedPreference.getFloat(key, value);
    }

    @Override
    Boolean invokeBoolean(Boolean value) {
        return sharedPreference.getBoolean(key, value);
    }
}
