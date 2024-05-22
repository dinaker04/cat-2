package com.example.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button b1, b2, b3, b4;
    EditText t1, t2, t3;
    TextView display;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

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
        display = findViewById(R.id.textView);

        // Initializing shared preferences
        preferences = getSharedPreferences("AravindhKannan", MODE_PRIVATE);
        editor = preferences.edit();

        // Reading data from shared preferences and setting it to EditText fields
        t1.setText(preferences.getString("id", ""));
        t2.setText(preferences.getString("name", ""));
        t3.setText(preferences.getString("email", ""));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save EditText values to shared preferences
                editor.putString("id", t1.getText().toString());
                editor.putString("name", t2.getText().toString());
                editor.putString("email", t3.getText().toString());
                editor.apply();

                // Insert data into database
                ContentValues values = new ContentValues();
                values.put("id", t1.getText().toString());
                values.put("name", t2.getText().toString());
                values.put("email", t3.getText().toString());
                db.insert("Aravindh", null, values);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from the database and display it
                Cursor c = db.rawQuery("select * from Aravindh", null);
                display.setText(""); // Clear previous data
                while (c.moveToNext()) {
                    display.append(c.getString(0) + "\n" + c.getString(1) + "\n" + c.getString(2) + "\n");
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
                db.update("Aravindh", values, "id = ?", new String[]{t1.getText().toString()});
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete data from the database
                db.delete("Aravindh", "id = ?", new String[]{t1.getText().toString()});
            }
        });
    }
}
