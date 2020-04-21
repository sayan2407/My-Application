package com.example.forpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        t1=findViewById(R.id.e1);
        t2=findViewById(R.id.e2);
        t3=findViewById(R.id.e3);

        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        t1.setText(bundle.getString("name"));
        t2.setText(bundle.getString("phone"));
        t3.setText(bundle.getString("add"));
    }
}
