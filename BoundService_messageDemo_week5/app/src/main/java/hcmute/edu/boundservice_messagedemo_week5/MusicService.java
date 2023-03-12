package hcmute.edu.boundservice_messagedemo_week5;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MusicService extends Service {

    public static final int MSG_PLAY_MUSIC = 1;
    private MediaPlayer mediaPlayer;
    private Messenger musicMessenger;

    public class MyHandler extends Handler {
        private Context applicationContext;

        public MyHandler(Context applicationContext) {
            this.applicationContext = applicationContext;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_PLAY_MUSIC:
                    startMusic();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MusicService", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Log.e("MusicService", "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MusicService", "onBind");
        musicMessenger = new Messenger(new MyHandler(this));
        return musicMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MusicService", "onUnBind");
        return super.onUnbind(intent);
    }

    private void startMusic() {
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.crying_music);
            mediaPlayer.start();
        }
    }
}
