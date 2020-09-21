package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
//    public final static String CONNECTION_URL = "http://192.168.1.114:8080";
    public final static String CONNECTION_URL = "http://192.168.43.34:8080";
    private Button btn1, btn2;

//Переход на стр регистрации и авторизации
    public void fncToNextWindow() {
        btn1 = (Button) findViewById(R.id.btnReg);
        btn2 = (Button) findViewById(R.id.btnIn);
        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent reg = new Intent(".Reg");
                        startActivity(reg);
                    }
                }
        );
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent(".In");
                        startActivity(in);
                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fncToNextWindow();
    }
}