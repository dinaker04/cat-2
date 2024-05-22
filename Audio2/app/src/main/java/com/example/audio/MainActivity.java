package com.example.audio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer music;
    private SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize MediaPlayer with the song
        music = MediaPlayer.create(this, R.raw.song);

        // Initialize SeekBar
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(music.getDuration()); // Set maximum value of seekbar to the duration of the song
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    music.seekTo(progress); // Seek to the specified position when user drags the seekbar
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void musicplay(View v) {
        music.start();
    }

    public void musicpause(View v) {
        music.pause();
    }

    public void musicstop(View v) {
        music.stop();
        music = MediaPlayer.create(this, R.raw.song);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (music != null) {
            music.release(); // Release MediaPlayer when activity is destroyed
        }
    }
}