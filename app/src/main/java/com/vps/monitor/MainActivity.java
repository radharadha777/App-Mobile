package com.vps.monitor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;

import org.json.JSONObject;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText ipBox, portBox, passBox;
    TextView cpuView, ramView, diskView, contView;
    SharedPreferences prefs;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipBox = findViewById(R.id.ipBox);
        portBox = findViewById(R.id.portBox);
        passBox = findViewById(R.id.passBox);
        cpuView = findViewById(R.id.cpuView);
        ramView = findViewById(R.id.ramView);
        diskView = findViewById(R.id.diskView);
        contView = findViewById(R.id.contView);

        prefs = getSharedPreferences("VPSMonitor", MODE_PRIVATE);

        ipBox.setText(prefs.getString("ip", ""));
        portBox.setText(prefs.getString("port", "5000"));
        passBox.setText(prefs.getString("password", ""));

        Button saveBtn = findViewById(R.id.saveBtn);
        Button fetchBtn = findViewById(R.id.fetchBtn);

        saveBtn.setOnClickListener(v -> {
            prefs.edit()
                .putString("ip", ipBox.getText().toString())
                .putString("port", portBox.getText().toString())
                .putString("password", passBox.getText().toString())
                .apply();
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        });

        fetchBtn.setOnClickListener(v -> fetchMetrics());
    }

    private void fetchMetrics() {
        String url = "http://" + ipBox.getText().toString() + ":" +
                     portBox.getText().toString() +
                     "/metrics?password=" + passBox.getText().toString();

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String body = response.body().string();
                    JSONObject json = new JSONObject(body);
                    String cpu = json.getString("cpu_percent");
                    String ram = json.getJSONObject("memory").getString("used") + "/" + json.getJSONObject("memory").getString("total");
                    String disk = json.getJSONObject("disk").getString("used") + "/" + json.getJSONObject("disk").getString("total");
                    String containers = json.getString("containers");

                    runOnUiThread(() -> {
                        cpuView.setText("CPU: " + cpu + "%");
                        ramView.setText("RAM: " + ram);
                        diskView.setText("Disk: " + disk);
                        contView.setText("Containers: " + containers);
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Parse error", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}
