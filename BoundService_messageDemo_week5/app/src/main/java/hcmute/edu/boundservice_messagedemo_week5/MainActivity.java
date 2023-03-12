package hcmute.edu.boundservice_messagedemo_week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Messenger musicMessenger;
    private boolean isServiceConnected;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicMessenger = new Messenger(iBinder);
            isServiceConnected = true;
            // send message play music
            sendMessageMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            musicMessenger = null;
            isServiceConnected = false;
        }
    };

    private void sendMessageMusic() {
        Message message = Message.obtain(null, MusicService.MSG_PLAY_MUSIC, 0, 0);
        try {
            musicMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startService = findViewById(R.id.btn_startService);
        Button stopService = findViewById(R.id.btn_stopService);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCLickStartService();
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCLickStopService();
            }
        });
    }
    private void onCLickStartService() {
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void onCLickStopService() {
        if (isServiceConnected) {
            unbindService(serviceConnection);
            isServiceConnected = false;
        }
    }

}