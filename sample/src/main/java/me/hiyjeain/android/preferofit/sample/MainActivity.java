package me.hiyjeain.android.preferofit.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.hiyjeain.android.preferofit.Preferofit;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Preferofit preferofit = new Preferofit(getApplication(), "test", MODE_PRIVATE);
        testService = preferofit.create(TestService.class);
    }

    private Preferofit preferofit;

    private TestService testService;

    @OnClick(R.id.button_set)
    void testSet() {
        testService
                .setTest(new Date().toString())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getApplicationContext(), "Get OnCompleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Get OnError" + e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(getApplicationContext(), "Get OnNext" + s, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.button_get)
    void testGet() {
        testService
                .getTest("default Value")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getApplicationContext(), "Get OnCompleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Get OnError" + e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(getApplicationContext(), "Get OnNext" + s, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
