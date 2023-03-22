package hcmute.edu.testmaterial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DownloadedSongsActivity extends AppCompatActivity {

    private RecyclerView rclSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_songs);

        rclSong = findViewById(R.id.rcl_songs);
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