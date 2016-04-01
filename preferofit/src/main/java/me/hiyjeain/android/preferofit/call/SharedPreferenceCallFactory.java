package me.hiyjeain.android.preferofit.call;

import android.content.SharedPreferences;

import java.lang.reflect.Method;

import me.hiyjeain.android.preferofit.GET;
import me.hiyjeain.android.preferofit.SET;
import me.hiyjeain.android.preferofit.exception.InvalidSharedPreferenceMethodException;
import me.hiyjeain.android.preferofit.exception.UnsupportedSharedPreferenceActionException;

/**
 * Created by garrett on 4/1/16.
 */
public class SharedPreferenceCallFactory {
    public static SharedPreferenceCall create(SharedPreferences sharedPreferences, Method method, Class<?> returnType) {
        validateMethod(method, returnType);
        SharedPreferenceCall call;
        String key;
        Class<?> parameterType = method.getParameterTypes()[0];
        if(method.getAnnotation(GET.class) != null) {
            key = method.getAnnotation(GET.class).value();
            validateKey(key);
            call = new SharedPreferenceGetCall(key, returnType, parameterType, sharedPreferences);
        } else if(method.getAnnotation(SET.class) != null) {
            key = method.getAnnotation(SET.class).value();
            validateKey(key);
            call = new SharedPreferenceSetCall(key, returnType, parameterType, sharedPreferences);
        } else {
            throw new UnsupportedSharedPreferenceActionException("No supported SharePreference action found: ");
        }
        return call;
    }

    private static void validateMethod(Method method, Class<?> returnType) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if(parameterTypes.length != 1) {
            throw new InvalidSharedPreferenceMethodException("The parameter count must be 1, current is " + parameterTypes.length);
        }
        Class<?> parameterType = method.getParameterTypes()[0];
        if(!parameterType.equals(returnType)) {
            throw new InvalidSharedPreferenceMethodException(
                    "The type of parameter must be as same as the return type(or return generic type).\n" +
                            "Parameter Type: " + parameterType.getName() + "\n" +
                            "Return Type(or generic type): " + returnType);
        }
    }

    private static void validateKey(String key) {
        if(key == null || key.equals("")) {
            throw new InvalidSharedPreferenceMethodException("The SharedPreference key can not be empty.");
        }
    }
}
