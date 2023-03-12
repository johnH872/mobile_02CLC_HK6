package hcmute.edu.boundservice_binderdemo_week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MusicBoundService musicBoundService;
    private boolean isServiceConnected;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicBoundService.MyBinder myBinder = (MusicBoundService.MyBinder) iBinder;
            musicBoundService = myBinder.getMusicBoundService();
            musicBoundService.startMusic();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //Này chỉ chạy khi service tự động chết đột ngột
            isServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartService = findViewById(R.id.btn_startService);
        Button btnStopService = findViewById(R.id.btn_stopService);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStartService();
            }
        });
        
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStopService();
            }
        });
    }

    private void onClickStartService() {
        Intent intent = new Intent(this, MusicBoundService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void onClickStopService() {
        if (isServiceConnected) {
            unbindService(serviceConnection);
            isServiceConnected = false;
        }
    }
}