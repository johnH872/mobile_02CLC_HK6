package hcmute.edu.testmaterial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnToAlbum, btnToSingerSongs, btnFavoriteSongs, btnDownloadedSongs, btnPlayMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToAlbum = findViewById(R.id.btn_toAlbumActivity);
        btnToSingerSongs = findViewById(R.id.btn_toSingerAlbum);
        btnFavoriteSongs = findViewById(R.id.btn_toFavoriteSongs);
        btnDownloadedSongs = findViewById(R.id.btn_toDownloadedAlbum);
        btnPlayMusic = findViewById(R.id.btn_toPlayMusic);

        btnToAlbum.setOnClickListener(this);
        btnToSingerSongs.setOnClickListener(this);
        btnFavoriteSongs.setOnClickListener(this);
        btnDownloadedSongs.setOnClickListener(this);
        btnPlayMusic.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == btnToAlbum) {
            Intent intent = new Intent(this, AlbumActivity.class);
            startActivity(intent);
        } else if (view == btnToSingerSongs) {
            Intent intent = new Intent(this, SingerSongsActivity.class);
            startActivity(intent);
        } else if (view == btnFavoriteSongs) {
            Intent intent = new Intent(this, FavoriteSongsActivity.class);
            startActivity(intent);
        } else if (view == btnDownloadedSongs) {
            Intent intent = new Intent(this, DownloadedSongsActivity.class);
            startActivity(intent);
        } else if (view == btnPlayMusic) {
            Intent intent = new Intent(this, PlayMusicActivity.class);
            startActivity(intent);
        }
    }
}