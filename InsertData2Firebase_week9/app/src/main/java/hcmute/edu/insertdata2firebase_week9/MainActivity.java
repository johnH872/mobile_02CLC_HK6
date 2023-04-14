package hcmute.edu.insertdata2firebase_week9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtAge, txtPhoneNo, txtHeight;
    Button btnSave;
    DatabaseReference reff;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.edt_name);
        txtAge = findViewById(R.id.edt_age);
        txtPhoneNo = findViewById(R.id.edt_phoneNo);
        txtHeight = findViewById(R.id.edt_height);
        btnSave = findViewById(R.id.btn_save);
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        member = new Member();
        btnSave.setOnClickListener(view -> {
            int age = Integer.parseInt(txtAge.getText().toString().trim());
            Float height = Float.parseFloat(txtHeight.getText().toString().trim());
            Long PhoneNo = Long.parseLong(txtPhoneNo.getText().toString().trim());

            member.setName(txtName.getText().toString().trim());
            member.setAge(age);
            member.setHt(height);
            member.setPh(PhoneNo);

            reff.push().setValue(member);
            Toast.makeText(this, "data inserted successfully", Toast.LENGTH_LONG).show();
        });
    }
}