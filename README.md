#Preferofit

A Retrofit-like SharedPreference access library by [HiyJeain](http://blog.hiyjeain.me)

##Usage

###Interface Declaration

	public interface TestService {
	    @SET("test")
	    Observable<String> setTest(String value);

	    @GET("test")
	    Observable<String> getTest(String defaultValue);
	}

###Interface Creation

    Preferofit preferofit = new Preferofit(getApplication(), "test", MODE_PRIVATE);
	testService = preferofit.create(TestService.class);

###Use it!
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
              }});

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
              }});
##License
    Copyright 2016 Hiyjeain
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
