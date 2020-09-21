package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuAfteAuth extends AppCompatActivity {

    private Button btn1, btn2,btn3, btn4, btn5;
    public void fncBtnOnMainMenu() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent window1 = new Intent(".PersonalOffice");
                        startActivity(window1);
                    }
                }
        );
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent window2 = new Intent(".Calculator");
                        startActivity(window2);
                    }
                }
        );
        btn3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent window3 = new Intent(".About_the_disease");
                        startActivity(window3);
                    }
                }
        );
        btn4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent window4 = new Intent(".Reg");
                        startActivity(window4);
                    }
                }
        );
        btn5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent window5 = new Intent(".Tourism");
                        startActivity(window5);
                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_afte_auth);
        fncBtnOnMainMenu();
    }
}