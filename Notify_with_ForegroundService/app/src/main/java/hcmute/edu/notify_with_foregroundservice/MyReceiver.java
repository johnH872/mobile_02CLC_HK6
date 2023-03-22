package hcmute.edu.notify_with_foregroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int actionMusic = intent.getIntExtra("action_music", 0);
        Log.e("Test", String.valueOf(actionMusic));

        Intent intentService = new Intent(context, MyService.class);
        intentService.putExtra("action_music_service", actionMusic);

        context.startService(intentService);
    }
}
