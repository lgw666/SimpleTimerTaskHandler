package com.gavinrowe.lgw.simpletimertaskhandler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gavinrowe.lgw.library.SimpleTimerTask;
import com.gavinrowe.lgw.library.SimpleTimerTaskHandler;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SimpleTimerTask task = new SimpleTimerTask() {
            @Override
            public void run() {
                Log.e(TAG, "run: task");
            }
        };
        SimpleTimerTask loopTask = new SimpleTimerTask(5000) {
            @Override
            public void run() {
                Log.e(TAG, "run: loop task，loop interval：" + getLoopInterval());
            }
        };
        final SimpleTimerTaskHandler handler = SimpleTimerTaskHandler.getInstance();

        handler.sendTask(0, task);
//        handler.sendTaskDelayed(1, task, 1000);
//        handler.sendTimerTask(2, task, 17);
//        handler.sendTimerTask(3, task, 17, 12);
//        handler.sendTimerTask(4, task, 17, 12, 50);
//
        handler.sendTask(5, loopTask);
//        handler.sendTaskDelayed(6, loopTask, 1000);
//        handler.sendTimerTask(7, loopTask, 17);
//        handler.sendTimerTask(8, loopTask, 17, 12);
//        handler.sendTimerTask(9, loopTask, 17, 12, 50);

    }
}
