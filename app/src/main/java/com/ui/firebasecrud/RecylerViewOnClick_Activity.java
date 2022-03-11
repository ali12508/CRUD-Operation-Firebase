package com.ui.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecylerViewOnClick_Activity extends AppCompatActivity {
    TextView t1,t2,t3;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view_on_click);
        t1 = findViewById(R.id.onclickphoneNo);
        t2 = findViewById(R.id.onclickAge);
        t3 = findViewById(R.id.onclicknametext);
        circleImageView = findViewById(R.id.onclickimg);

        circleImageView.setImageResource(getIntent().getIntExtra("image", R.drawable.avatar));
        t1.setText(getIntent().getStringExtra("phone"));
        t2.setText(getIntent().getStringExtra("age"));
        t3.setText(getIntent().getStringExtra("name"));
    }}