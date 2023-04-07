package hcmute.edu.mediaplayer_week8;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song1);
        Button bPlay = findViewById(R.id.playButton);
        Button bPause = findViewById(R.id.pauseButton);
        Button bStop = findViewById(R.id.stopButton);

        bPlay.setOnClickListener(view -> {
            mediaPlayer.start();
        });

        bPause.setOnClickListener(view -> {
            mediaPlayer.pause();
        });

        bStop.setOnClickListener(view -> {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}