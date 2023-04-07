package hcmute.edu.accessmp3file_week8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_getAllMp3Files = findViewById(R.id.getAllMp3Files);
        btn_getAllMp3Files.setOnClickListener(view -> {
            Intent intent = new Intent(this, ReadMp3.class);
            startActivity(intent);
        });
    }
}