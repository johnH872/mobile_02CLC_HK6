package hcmute.edu.activity_foregroundservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStartService;
    private Button btnStopService;
    private RelativeLayout layoutBottom;
    private ImageView imgSong, imgPlayOrPause, imgClose;
    private TextView tvTitleSong, tvSingerSong;
    private Song mSong;
    private boolean isPlaying;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle == null){
                return;
            }

            mSong = (Song) bundle.get("object_song");
            isPlaying = bundle.getBoolean("status_player");
            int actionMusic = bundle.getInt("action_music");
            handleLayoutMusic(actionMusic);
        }
    };

    private void handleLayoutMusic(int action) {
        switch (action) {
            case MyService.ACTION_START:
                layoutBottom.setVisibility(View.VISIBLE);
                showInfoSong();
                setStatusPlayOrPause();
                break;
            case MyService.ACTION_PAUSE:
                setStatusPlayOrPause();
                break;
            case MyService.ACTION_RESUME:
                setStatusPlayOrPause();
                break;
            case MyService.ACTION_CLEAR:
                layoutBottom.setVisibility(View.GONE);
                break;
        }
    }

    private void showInfoSong() {
        if (mSong == null) return;
        imgSong.setImageResource(mSong.getImage());
        tvTitleSong.setText(mSong.getTittle());
        tvSingerSong.setText(mSong.getSinger());

        imgPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    senActionToService(MyService.ACTION_PAUSE);
                } else {
                    senActionToService(MyService.ACTION_RESUME);
                }
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senActionToService(MyService.ACTION_CLEAR);
            }
        });
    }

    private void setStatusPlayOrPause() {
        if (isPlaying) {
            imgPlayOrPause.setImageResource(R.drawable.pause);
        } else {
            imgPlayOrPause.setImageResource(R.drawable.ic_play);
        }
    }

    private void senActionToService(int action) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("action_music_service", action);
        startService(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("send_data_to_activity"));


        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);
        layoutBottom = findViewById(R.id.layout_bottom);
        imgSong = findViewById(R.id.img_song);
        imgPlayOrPause = findViewById(R.id.img_play_or_pause);
        imgClose = findViewById(R.id.img_close);
        tvTitleSong = findViewById(R.id.tv_title_song);
        tvSingerSong = findViewById(R.id.tv_singer_song);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnStartService) {
            clickStartService();
        } else if (view == btnStopService) {
            clickStopService();
        }
    }

    private void clickStartService() {
        Song song = new Song("Crying in the club", "Camila Cabello", R.drawable.img_music, R.raw.crying_music);
        Intent intent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_song", song);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void clickStopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

}