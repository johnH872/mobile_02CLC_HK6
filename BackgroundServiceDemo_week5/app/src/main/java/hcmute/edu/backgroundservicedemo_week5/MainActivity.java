package hcmute.edu.backgroundservicedemo_week5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button stopService, startService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = findViewById(R.id.btn_startService);
        stopService = findViewById(R.id.btn_stopService);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService();
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService();
            }
        });
    }
    public void startService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void stopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}