package me.hiyjeain.android.preferofit;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by garrett on 3/31/16.
 */
public class Preferofit {

    private final Map<Method, MethodHandler> methodHandlerCache = new LinkedHashMap<>();

    private SharedPreferences sharedPreferences;

    public Preferofit(Application application, String name, int mode) {
        this(application.getSharedPreferences(name, mode));
    }

    public Preferofit(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    SharedPreferences sharedPreferences() {
        return sharedPreferences;
    }

    @SuppressWarnings("unchecked") // Single-interface proxy creation guarded by parameter safety.
    public <T> T create(final Class<T> service) {
    return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object... args) throws Throwable {
                    Log.e("xujunwei", "test");
                    if (method.getDeclaringClass() == Object.class) {
                        return method.invoke(this, args);
                    }
                    return loadMethodHandler(method).invoke(args);
                }
            });
    }

    MethodHandler loadMethodHandler(Method method) {
        MethodHandler handler;
        synchronized (methodHandlerCache) {
            handler = methodHandlerCache.get(method);
            if (handler == null) {
                handler = MethodHandler.create(this, method);
                methodHandlerCache.put(method, handler);
            }
        }
        return handler;
    }
}
