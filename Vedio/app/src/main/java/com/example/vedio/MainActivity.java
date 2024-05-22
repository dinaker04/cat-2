package com.example.vedio;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private SeekBar seekBar;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize VideoView
        videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vedio));

        // Initialize MediaController
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // Initialize SeekBar
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(videoView.getDuration()); // Set maximum value of seekbar to the duration of the video
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress); // Seek to the specified position when user drags the seekbar
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void playVideo(View v) {
        videoView.start();
    }

    public void pauseVideo(View v) {
        videoView.pause();
    }

    public void stopVideo(View v) {
        videoView.stopPlayback();
        videoView.resume(); // Reset the video to the beginning
    }
}
