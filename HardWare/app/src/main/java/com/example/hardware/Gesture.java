package com.example.hardware;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public class Gesture extends AppCompatActivity {
    private ImageView img;
    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private ScaleGestureDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        img = (ImageView)findViewById(R.id.imgvw);
        detector = new ScaleGestureDetector(Gesture.this,new
                ScaleListener());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        detector.onTouchEvent(event);
        return true;
    }
    private class ScaleListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));
            matrix.setScale(scale, scale);
            img.setImageMatrix(matrix);
            return true;
        }
    }
}
