package hcmute.edu.internalstorage_week7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String fileName = "demoFile.txt";
    Button btn_read, btn_write;
    EditText user_input;
    TextView file_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_read = findViewById(R.id.btn_read);
        btn_write = findViewById(R.id.btn_write);
        user_input = findViewById(R.id.edit_value);
        file_content = findViewById(R.id.tv_read);

        btn_write.setOnClickListener(this);
        btn_read.setOnClickListener(this);
    }

    public void printMessage(String m) {
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }

    private void readData() {
        try {
            FileInputStream fin = openFileInput(fileName);
            int a;
            StringBuilder temp = new StringBuilder();
            while ((a = fin.read()) != -1) {
                temp.append((char) a);
            }
            file_content.setText(temp.toString());
            fin.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        printMessage("reading to file " + fileName + " completed..");
    }

    private void writeData() {
        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            String data = user_input.getText().toString();
            fos.write(data.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        user_input.setText("");
        printMessage("writing to file " + fileName + " completed..");
    }


    @Override
    public void onClick(View view) {
        if (view == btn_read){
            readData();
        }
        if (view == btn_write) {
            writeData();
        }
    }
}