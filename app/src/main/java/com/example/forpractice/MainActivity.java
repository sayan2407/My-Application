package com.example.forpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.LocalDate.now;

public class MainActivity extends AppCompatActivity {

    EditText e1, e2, e3;
    String n, p, a;
    Button b, call,button,sms;
    TextView date,time;
    LocalDate d;
    LocalTime t;
    String number,msg;
    final int SEND_SMS_PERMISSION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        b = findViewById(R.id.b1);
        call = findViewById(R.id.b2);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        sms=findViewById(R.id.sms);
        button=findViewById(R.id.datetime);


        if (checkpermission(Manifest.permission.SEND_SMS)){
            sms.setEnabled(true);
        }else {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION);
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+e1.getText().toString()));

                startActivity(i);

            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=e1.getText().toString().trim();
                msg=e2.getText().toString().trim();
                if (checkpermission(Manifest.permission.SEND_SMS))
                {
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(number,null,msg,null,null);
                    Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
                }


            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=e1.getText().toString().trim();
                p=e2.getText().toString().trim();
                a=e3.getText().toString().trim();
                Bundle bundle=new Bundle();
                bundle.putString("name",n);
                bundle.putString("phone",p);
                bundle.putString("add",a);
                Intent i=new Intent(getApplicationContext(),SecondActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    d= now();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    t=LocalTime.now();
                }
                date.setText(d.toString());
                time.setText(t.toString());


            }
        });
    }
    public boolean checkpermission(String Permission)
    {
        int check= ContextCompat.checkSelfPermission(this,Permission);
        return (check== PackageManager.PERMISSION_GRANTED);
    }
}
