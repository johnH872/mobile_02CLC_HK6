package hcmute.edu.backgroundservicedemo_week5;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends IntentService {

    public MyService() {
        super("My Intent Thread");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(MyService.this, "Service started", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(MyService.this, "Service destroyed", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        synchronized (this) {
            try {
                wait(20000);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}