package hcmute.edu.startactivityforresultdemo_week4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        setTitle("Activity 2");

        Intent intent = getIntent();
        int number1 = intent.getIntExtra("number 1", 0);
        int number2 = intent.getIntExtra("number 2", 0);

        TextView textView = findViewById(R.id.text_view_numbers);
        textView.setText("Numbers: " + number1 + ", " + number2);

        Button btnAdd = (Button) findViewById(R.id.button_add);
        Button btnSubtract = (Button)  findViewById(R.id.button_subtract);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = number1 + number2;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", result);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = number1 - number2;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", result);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}