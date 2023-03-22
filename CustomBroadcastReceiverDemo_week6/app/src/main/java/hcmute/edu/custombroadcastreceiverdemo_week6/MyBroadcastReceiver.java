package hcmute.edu.custombroadcastreceiverdemo_week6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("hcmute.edu.EXAMPLE_ACTION".equals(intent.getAction())) {
            String receivedText = intent.getStringExtra("hcmute.edu.EXTRA_TEXT");
            Toast.makeText(context, receivedText, Toast.LENGTH_SHORT).show();
        }
    }
}
