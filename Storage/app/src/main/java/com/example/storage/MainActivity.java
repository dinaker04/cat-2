package com.example.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button b1, b2, b3, b4;
    EditText t1, t2, t3, t4, t5;
    TextView display;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private static final String TAG = "MainActivity";
    private static final int REQUEST_POST_NOTIFICATIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper helper = new DBHelper(getApplicationContext());
        db = helper.getReadableDatabase();
        db = helper.getWritableDatabase();
        b1 = findViewById(R.id.create);
        b2 = findViewById(R.id.read);
        b3 = findViewById(R.id.update);
        b4 = findViewById(R.id.delete);
        t1 = findViewById(R.id.id);
        t2 = findViewById(R.id.name);
        t3 = findViewById(R.id.email);
        t4 = findViewById(R.id.regno);
        t5 = findViewById(R.id.slot);
        display = findViewById(R.id.textView);

        // Initializing shared preferences
        preferences = getSharedPreferences("AravindhKannan", MODE_PRIVATE);
        editor = preferences.edit();

        // Reading data from shared preferences and setting it to EditText fields
        t1.setText(preferences.getString("id", ""));
        t2.setText(preferences.getString("name", ""));
        t3.setText(preferences.getString("email", ""));
        t4.setText(preferences.getString("regno", ""));
        t5.setText(preferences.getString("slot", ""));

        // Request notification permission if needed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_POST_NOTIFICATIONS);
            }
        }

        // Create notification channel
        createNotificationChannel();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save EditText values to shared preferences
                editor.putString("id", t1.getText().toString());
                editor.putString("name", t2.getText().toString());
                editor.putString("email", t3.getText().toString());
                editor.putString("regno", t4.getText().toString());
                editor.putString("slot", t5.getText().toString());
                editor.apply();

                // Insert data into database
                ContentValues values = new ContentValues();
                values.put("id", t1.getText().toString());
                values.put("name", t2.getText().toString());
                values.put("email", t3.getText().toString());
                values.put("regno", t4.getText().toString());
                values.put("slot", t5.getText().toString());
                db.insert("Student", null, values);

                // Push notification
                Log.d(TAG, "Creating notification for student creation");
                pushNotification("Student Created", "Student with ID " + t1.getText().toString() + " has been created.");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from the database and display it
                Cursor c = db.rawQuery("select * from Student", null);
                display.setText(""); // Clear previous data
                while (c.moveToNext()) {
                    display.append(c.getString(0) + "\n" + c.getString(1) + "\n" + c.getString(2) + "\n" +c.getString(3)
                            + "\n" +c.getString(4) +"\n");
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update data in the database
                ContentValues values = new ContentValues();
                values.put("name", t2.getText().toString());
                values.put("email", t3.getText().toString());
                values.put("regno", t4.getText().toString());
                values.put("slot", t5.getText().toString());
                db.update("Student", values, "id = ?", new String[]{t1.getText().toString()});

                // Push notification
                Log.d(TAG, "Creating notification for student update");
                pushNotification("Student Updated", "Student with ID " + t1.getText().toString() + " has been updated.");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete data from the database
                db.delete("Student", "id = ?", new String[]{t1.getText().toString()});

                // Push notification
                Log.d(TAG, "Creating notification for student deletion");
                pushNotification("Student Deleted", "Student with ID " + t1.getText().toString() + " has been deleted.");
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "StudentCRUDChannel";
            String description = "Channel for student CRUD operations notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("studentCRUDChannel", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void pushNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "studentCRUDChannel")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Log.d(TAG, "Sending notification: " + title + " - " + message);
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());  // Use a unique ID for each notification
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_POST_NOTIFICATIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Notification permission granted");
            } else {
                Log.d(TAG, "Notification permission denied");
            }
        }
    }
}
