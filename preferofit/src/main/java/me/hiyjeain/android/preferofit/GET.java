package me.hiyjeain.android.preferofit;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by garrett on 3/31/16.
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface GET {
    String value() default "_default_key";
}
