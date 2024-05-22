package com.example.graphics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageview);

        // Load all animations
        Animation blinkAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        Animation rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        Animation fadeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
        Animation crossFadeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.crossf);
        Animation zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
        Animation zoomOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomout);
        Animation moveAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        Animation slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup);
        Animation slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slided);
        Animation bounceAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        Animation sequentialAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.seq);
        Animation togetherAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.together);

        // Set animation listeners to start the next animation after the previous one finishes
        blinkAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Blink Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(rotateAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Rotate Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(fadeAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        fadeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Fade Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(fadeOutAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Fade Out Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(crossFadeAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        crossFadeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Cross Fade Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(zoomInAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        zoomInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Zoom In Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(zoomOutAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        zoomOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Zoom Out Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(moveAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        moveAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Move Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(slideUpAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        slideUpAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Slide Up Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(slideDownAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        slideDownAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Slide Down Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(bounceAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        bounceAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Bounce Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(sequentialAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        sequentialAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Sequential Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(togetherAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        togetherAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "Together Animation Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // All animations finished
                Toast.makeText(MainActivity.this, "All Animations Finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

// Start the first animation in the sequence with delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(blinkAnimation);
            }
        }, 1000);

// Add delays between animations
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(rotateAnimation);
            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(fadeAnimation);
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(fadeOutAnimation);
            }
        }, 4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(crossFadeAnimation);
            }
        }, 5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(zoomInAnimation);
            }
        }, 6000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(zoomOutAnimation);
            }
        }, 7000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(moveAnimation);
            }
        }, 8000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(slideUpAnimation);
            }
        }, 9000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(slideDownAnimation);
            }
        }, 10000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(bounceAnimation);
            }
        }, 11000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(sequentialAnimation);
            }
        }, 12000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(togetherAnimation);
            }
        }, 13000);
    }
}

