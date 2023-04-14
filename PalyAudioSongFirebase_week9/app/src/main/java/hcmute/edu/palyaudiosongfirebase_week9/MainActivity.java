package hcmute.edu.palyaudiosongfirebase_week9;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnPlayMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlayMusic = findViewById(R.id.btn_playMusic);
        btnPlayMusic.setOnClickListener(view -> {
            play_Song();
        });
    }

    private void play_Song() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/bt-tuan9.appspot.com/o/I-Am-IVE.mp3?alt=media&token=730bde29-d99d-4e81-ba78-d28f4a1c5551");
            mediaPlayer.setOnPreparedListener(mediaPlayer1 -> mediaPlayer1.start());
            mediaPlayer.prepare();
            Toast.makeText(this, "Play song", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}