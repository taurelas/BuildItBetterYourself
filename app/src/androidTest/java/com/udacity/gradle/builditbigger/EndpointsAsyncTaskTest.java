package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest  {

    private String result;


    /** As per
     *
     * https://stackoverflow.com/a/3802487/3886459
     *
     * @throws InterruptedException in CountDownLatch's await()
     */
    @Test
    public void getJokeTest() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        new EndpointsAsyncTask().execute(new ActivityCallback() {
            @Override
            public void onResult(String joke) {
                signal.countDown();
                result = joke;
            }
        });

        signal.await();
        Log.d("Test", result);
        assertEquals(false, TextUtils.isEmpty(result));

    }

}