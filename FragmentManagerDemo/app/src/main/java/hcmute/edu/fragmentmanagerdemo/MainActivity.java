package hcmute.edu.fragmentmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.btnFragment1);
        Button btn2 = (Button)findViewById(R.id.btnFragment2);

        FragmentManager fragmentManager = getSupportFragmentManager();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.flFragment, new FirstFragment())
                        .addToBackStack("stack")
                        .commit();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.flFragment, new SecondFragment())
                        .addToBackStack("stack")
                        .commit();
            }
        });
    }
}