package com.example.a35example;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager = null;
    private TextView textView;
    private MediaPlayer mp;

    @Override
    public void onSensorChanged(SensorEvent event) {
        if( event.sensor.getType() == Sensor.TYPE_LIGHT)
        {
            textView.setText("Light: " + event.values[0]);
            if (event.values[0] < 20) {
                mp.start();
            }
            else {
                mp.pause();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.song);
        textView = (TextView)findViewById(R.id.textView);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}