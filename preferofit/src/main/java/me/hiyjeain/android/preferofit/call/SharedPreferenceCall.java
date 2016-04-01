package me.hiyjeain.android.preferofit.call;

import android.content.SharedPreferences;

import me.hiyjeain.android.preferofit.exception.UnsupportedSharedPreferenceTypeException;

/**
 * Created by garrett on 4/1/16.
 */
public abstract class SharedPreferenceCall {

    protected String key;
    protected Class returnType;
    protected Class argumentType;
    protected SharedPreferences sharedPreference;

    SharedPreferenceCall(String key, Class returnType, Class argumentType, SharedPreferences sharedPreference) {
        this.key = key;
        this.returnType = returnType;
        this.argumentType = argumentType;
        this.sharedPreference = sharedPreference;
    }

    SharedPreferenceCall(String key, Class returnType, Class argumentType) {
        this.key = key;
        this.returnType = returnType;
        this.argumentType = argumentType;
    }

    void setSharedPreference(SharedPreferences sharedPreference) {
        this.sharedPreference = sharedPreference;
    }

    public final Object invoke(Object... args) {
        if(returnType.equals(String.class)) {
            return invokeString((String) args[0]);
        } else if(returnType.equals(Integer.class)){
            return invokeInteger((Integer) args[0]);
        } else if(returnType.equals(Long.class)) {
            return invokeLong((Long) args[0]);
        } else if(returnType.equals(Float.class)) {
            return invokeFloat((Float) args[0]);
        } else if(returnType.equals(Boolean.class)) {
            return invokeBoolean((Boolean) args[0]);
        }
        throw new UnsupportedSharedPreferenceTypeException("The type \"" + returnType.getName() + "\" is not support!");
    }

    abstract String invokeString(String value);
    abstract Integer invokeInteger(Integer value);
    abstract Long invokeLong(Long value);
    abstract Float invokeFloat(Float value);
    abstract Boolean invokeBoolean(Boolean value);
}
