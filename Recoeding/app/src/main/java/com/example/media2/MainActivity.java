package com.example.media2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private MediaRecorder mediaRecorder;
    private SurfaceHolder surfaceHolder;
    private Button buttonRecord;
    private Button buttonPlayback;
    private boolean isRecording = false;
    private String outputFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        buttonRecord = findViewById(R.id.button_record);
        buttonPlayback = findViewById(R.id.button_playback);

        outputFile = getExternalCacheDir().getAbsolutePath() + "/recorded_video.mp4";
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        surfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (isRecording) {
            stopRecording();
        }
        releaseMediaRecorder();
    }

    public void toggleRecording(View view) {
        if (!isRecording) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        if (prepareMediaRecorder()) {
            try {
                mediaRecorder.start();
                isRecording = true;
                buttonRecord.setText("Stop Recording");
                buttonPlayback.setVisibility(View.GONE);
                Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                releaseMediaRecorder();
                isRecording = false;
                buttonRecord.setText("Record");
                buttonPlayback.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Failed to start recording", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to prepare MediaRecorder", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        try {
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
            buttonRecord.setText("Record");
            buttonPlayback.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show();

            // After recording stops, move the recorded video to desired location
            saveVideoToGallery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean prepareMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setVideoSize(320, 240); // Adjust size as needed
        mediaRecorder.setVideoFrameRate(30);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setOutputFile(outputFile);
        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
        mediaRecorder.setOrientationHint(90); // Set orientation
        try {
            mediaRecorder.prepare();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            releaseMediaRecorder();
            return false;
        }
    }

    public void playbackVideo(View view) {
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(outputFile);
        videoView.start();

        // Show the Exit button when video playback starts
        Button buttonExit = findViewById(R.id.button_exit);
        buttonExit.setVisibility(View.VISIBLE);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceHolder = holder;

        // Set the preview display for the media recorder
        if (mediaRecorder != null) {
            mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
        }
    }

    private void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    private void saveVideoToGallery() {
        File source = new File(outputFile);
        File destination = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "RecordedVideos");

        if (!destination.exists()) {
            destination.mkdirs();
        }

        String destinationPath = destination.getAbsolutePath() + File.separator + "recorded_video.mp4";

        if (source.exists()) {
            source.renameTo(new File(destinationPath));

            // Add the video to the gallery so it's visible in the device's media player
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(new File(destinationPath));
            mediaScanIntent.setData(uri);
            sendBroadcast(mediaScanIntent);
        }
    }

    public void exitApp(View view) {
        finish(); // Close the activity
    }
}
