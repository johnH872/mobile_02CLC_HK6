package hcmute.edu.testmaterial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    private RecyclerView rclSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        rclSong = findViewById(R.id.rcl_song);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rclSong.setLayoutManager(gridLayoutManager);

        SongAdapter songAdapter = new SongAdapter(getListSong());
        rclSong.setAdapter(songAdapter);

        Toolbar toolBar = findViewById(R.id.topAppBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private List<Song> getListSong() {
        List<Song> list = new ArrayList<>();
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        list.add(new Song(R.drawable.alec_album, "Let me down slowly", "Alec Benjamin"));
        return list;
    }
}