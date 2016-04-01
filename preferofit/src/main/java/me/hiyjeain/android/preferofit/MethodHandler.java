package me.hiyjeain.android.preferofit;

import java.lang.reflect.Method;

import me.hiyjeain.android.preferofit.call.SharedPreferenceCall;
import me.hiyjeain.android.preferofit.call.SharedPreferenceCallFactory;
import me.hiyjeain.android.preferofit.exception.PreferofitException;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by garrett on 4/1/16.
 */
class MethodHandler {
    private static boolean isRxEnabled = true;

    Object invoke(final Object... args) {
        if(isRxEnabled) {
            return Observable.create(new Observable.OnSubscribe<Object>() {
                @Override
                public void call(Subscriber<? super Object> subscriber) {
                    if(!subscriber.isUnsubscribed()) {
                        subscriber.onNext(sharedPreferenceCall.invoke(args));
                        subscriber.onCompleted();
                    }
                }
            });
        } else {
            return this.sharedPreferenceCall.invoke(args);
        }
    }

    private MethodHandler(SharedPreferenceCall call){
        this.sharedPreferenceCall = call;
    }

    private SharedPreferenceCall sharedPreferenceCall;

    public static MethodHandler create(Preferofit preferofit, Method method) {
        validateMethod(method);
        return new MethodHandler(SharedPreferenceCallFactory.create(preferofit.sharedPreferences(), method, getReturnClass(method)));
    }

    private static Class<?> getReturnClass(Method method) {
        if(isRxEnabled) {
            String returnTypeString = method.getGenericReturnType().toString();
            String genericTypeString = returnTypeString.substring(returnTypeString.indexOf('<') + 1, returnTypeString.lastIndexOf('>'));
            try {
                return ClassLoader.getSystemClassLoader().loadClass(genericTypeString);
            } catch (ClassNotFoundException e) {
                throw new PreferofitException("The generic type can not be found in SystemClassLoader:" + genericTypeString);
            }
        } else {
            return method.getReturnType();
        }
    }


    private static void validateMethod(Method method) {
        if(isRxEnabled) {
            if(!method.getReturnType().equals(Observable.class)) {
                throw new PreferofitException("RxJava is enable, but the return type is not rx.Observable, current is " + method.getReturnType().getName());
            }
            String returnTypeString = method.getGenericReturnType().toString();
            String genericTypeString = returnTypeString.substring(returnTypeString.indexOf('<') + 1, returnTypeString.lastIndexOf('>'));
            if(genericTypeString.contains(",") || genericTypeString.contains("<") || genericTypeString.contains(">")) {
                throw new PreferofitException("The generic type count must be just one none-generic class, current is " + genericTypeString);
            }
            try {
                ClassLoader.getSystemClassLoader().loadClass(genericTypeString);
            } catch (ClassNotFoundException e) {
                throw new PreferofitException("The generic type can not be found in SystemClassLoader:" + genericTypeString);
            }
        }
    }
}
