package com.example.hardware;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.net.wifi.WifiManager;
public class Wifi extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        Button btn = findViewById(R.id.wifiSwitch);
        TextView textView = findViewById(R.id.tv);
        WifiManager wifi = (WifiManager)
                getApplicationContext().getSystemService(WIFI_SERVICE);
        btn.setOnClickListener(view -> {
            wifi.setWifiEnabled(!wifi.isWifiEnabled());
            if (!wifi.isWifiEnabled()) {
                textView.setText("Wifi is ON");
            } else {
                textView.setText("Wifi is OFF");
            }
        });
    }
}
