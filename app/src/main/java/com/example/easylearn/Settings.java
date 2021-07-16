package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

public class Settings extends AppCompatActivity {

    public static SeekBar mseekBarPitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mseekBarPitch = (SeekBar) findViewById(R.id.seekBar);
    }
}
