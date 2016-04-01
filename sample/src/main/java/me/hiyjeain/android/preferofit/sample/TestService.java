package me.hiyjeain.android.preferofit.sample;

import me.hiyjeain.android.preferofit.GET;
import me.hiyjeain.android.preferofit.SET;
import rx.Observable;

/**
 * Created by garrett on 4/1/16.
 */
public interface TestService {
    @SET("test")
    Observable<String> setTest(String value);

    @GET("test")
    Observable<String> getTest(String defaultValue);
}
