package com.example.dangpermissions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnDial;
    EditText etPhNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDial=findViewById(R.id.btnDial);
        etPhNo=findViewById(R.id.etPhNo);
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              int perm=  ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE);
              if(perm== PackageManager.PERMISSION_GRANTED)
              {
                  callNumber();
              }
              else{
                  ActivityCompat.requestPermissions(
                          MainActivity.this,
                          new String[]{
                                  Manifest.permission.CALL_PHONE
                          },
                  121
                  );
              }
            }
        });
    }
    void callNumber()
    {
        String telNo=etPhNo.getText().toString();
        Uri uri= Uri.parse("tel:"+telNo);
        Intent i=new Intent(Intent.ACTION_CALL,uri);
        startActivity(i);
    }
}