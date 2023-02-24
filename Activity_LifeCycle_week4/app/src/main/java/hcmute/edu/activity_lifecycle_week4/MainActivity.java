package hcmute.edu.activity_lifecycle_week4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Activity Life Cycle: ", "On Create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Activity Life Cycle: ", "On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Activity Life Cycle: ", "On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Activity Life Cycle: ", "On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Activity Life Cycle: ", "On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Activity Life Cycle: ", "On Destroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Activity Life Cycle: ", "On Restart");
    }
}