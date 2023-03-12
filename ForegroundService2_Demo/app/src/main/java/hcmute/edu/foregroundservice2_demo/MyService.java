package hcmute.edu.foregroundservice2_demo;

import static hcmute.edu.foregroundservice2_demo.MyApplication.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Check", "MyService OnCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if (bundle!= null) {
            Song song = (Song) bundle.get("object_song");
            if(song != null) {
                startMusic(song);
                sendNotification(song);
            }
        }
        return START_NOT_STICKY;
    }

    private void startMusic(Song song) {
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getResource());
        }
        mediaPlayer.start();
    }

    private void sendNotification(Song song) {
        Intent intent = new Intent(this, MainActivity.class);
        // Ấn vào nó hiển thị lại ứng dụng đang xài
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), song.getImage());

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_custom_notification);
        remoteViews.setTextViewText(R.id.tv_title_song, song.getTittle());
        remoteViews.setTextViewText(R.id.tv_singer_song, song.getSinger());
        remoteViews.setImageViewBitmap(R.id.img_song, bitmap);
        remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.pause);
        remoteViews.setImageViewResource(R.id.img_close, R.drawable.close);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews)
                .setSound(null)
                .build();

        startForeground(1,notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Check", "MyService OnDestroy");
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
