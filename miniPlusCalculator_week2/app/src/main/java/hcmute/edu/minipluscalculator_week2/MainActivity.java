package hcmute.edu.minipluscalculator_week2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui);

        EditText editText1=(EditText)findViewById(R.id.inputA);
        EditText editText2=(EditText)findViewById(R.id.inputB);
        EditText result=(EditText)findViewById(R.id.result);
        Button calculateButton=(Button) findViewById(R.id.button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double sum,numA=0,numB=0;
                if (!editText1.getText().toString().isEmpty())
                    numA = Double.parseDouble(editText1.getText().toString());
                if (!editText2.getText().toString().isEmpty())
                    numB = Double.parseDouble(editText2.getText().toString());
                sum = numA + numB;
                result.setText(Double.toString(sum));
            }
        });
    }
}