package com.hhjt.wanchuan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hhjt.wanchuan.myapplication.romateLog.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.e("log","asdasdasdasssssssssssss222222222222222");
                Log.w("log","232222222222222222fsdfddddddddddddddd");
                Log.i("log","1546fsdfsdfsdfsd");
                Log.d("log","fsadfdsf111111111111111111111");
            }
        };
        timer.schedule(timerTask,2000,5000);

    }
}
