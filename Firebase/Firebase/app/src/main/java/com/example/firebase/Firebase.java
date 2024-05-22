package com.example.firebase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Firebase extends AppCompatActivity {
    EditText Name,Last_name,Age,Phone;
    Button InsertData;
    DatabaseReference ref;
    MainFile member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        Name=(EditText) findViewById(R.id.name);
        Last_name=(EditText) findViewById(R.id.last_name);
        Age=(EditText) findViewById(R.id.age);
        Phone=(EditText) findViewById(R.id.phone);
        InsertData=(Button) findViewById(R.id.btn_insert);
        FirebaseApp.initializeApp(this);
        ref= FirebaseDatabase.getInstance().getReference().child("Member");
        member = new MainFile();
        InsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int M_age=Integer.parseInt(Age.getText().toString().trim());
                Long M_phone=Long.parseLong(Phone.getText().toString().trim());
                member.setName(Name.getText().toString().trim());
                member.setLast_name(Last_name.getText().toString().trim());
                member.setAge(M_age);
                member.setPhone(M_phone);
                ref.push().setValue(member);
                Toast.makeText(Firebase.this, "Data Inserted",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}