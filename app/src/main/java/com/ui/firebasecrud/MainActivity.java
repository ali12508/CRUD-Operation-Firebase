package com.ui.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText names,pn,age,purl;
    DatabaseReference mreference;
    Button insert,view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(MainActivity.this);
        mreference= FirebaseDatabase.getInstance().getReference().child("Users");

        names=findViewById(R.id.name);
        pn=findViewById(R.id.phoneno);
        age=findViewById(R.id.age);
        purl=findViewById(R.id.imgUrl);
        insert=findViewById(R.id.btninsert);
        view=findViewById(R.id.btnview);

view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,RecylerViewActivity.class));
    }
});
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=names.getText().toString();
                String Ag=age.getText().toString();
                String Pn=pn.getText().toString();
                String Url=purl.getText().toString();

                if(TextUtils.isEmpty(name) &&(TextUtils.isEmpty(Ag) &&(TextUtils.isEmpty(Pn) &&(TextUtils.isEmpty(Url) )) )) {
                    names.setError("Name can not be empty");
                    pn.setError("Phone No can not be empty");
                    age.setError("Age can not be empty");
                    purl.setError("Image can not be empty");

                }
                /*      mref.push().setValue(user);*/ // multiple seprate users under node


                else {




//                    Toast.makeText(MainActivity.this, "Data Inserted Successfully.....", Toast.LENGTH_LONG).show();
//                    Intent intent1=new Intent(MainActivity.this,MainActivity.class);
//                    startActivity(intent1);

             ;
               User user=new User(name,Ag,Pn,Url);
               user.setName(name);
               user.setAge(Ag);
               user.setImage(Url);
               user.setPhone(Pn);
               mreference.push().setValue(user);
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully.....", Toast.LENGTH_LONG).show();
            }}
        });

    }
}