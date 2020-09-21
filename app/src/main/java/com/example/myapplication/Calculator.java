package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    private Switch switch_gender, switch_race; // переключатели пола и расы
    private EditText input_age, input_kreatin; // поля ввода возраста и уровня креатинина в крови
    private Button button_calc; // кнопка расчета результата
    private TextView text_result; //поле вывода результата
    private double genderCF; //коэффициент пола. Мужчины Пол = 1; альфа = -0.411; каппа = 0.9.
    // Женщины Пол = 1.018; альфа = -0.329; каппа = 0.7.
    private double genderAlpha; // коэффициент пола Альфа
    private double genderKappa; // коэффициент пола Каппа
    private double racialCF; // коэффициент расы. Европеоидная - 1,0, негроидная 1,159
    private int age; // возраст пользователя
    private double kreatinin, result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
//        calculate();
        addListenerOnButton();
    }
    public void addListenerOnButton(){
        text_result = (TextView) findViewById(R.id.text_result);
        button_calc = (Button) findViewById(R.id.button_calc);
        button_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (Double.toString(result).length()>0)
                calculate();
                text_result.setText(Double.toString(result));
            }
        });
    }
    public void calculate(){
        switch_gender = (Switch) findViewById(R.id.switch_gender);
        switch_race = (Switch) findViewById(R.id.switch_race);
        input_age = (EditText) findViewById(R.id.input_age);
        input_kreatin = (EditText) findViewById(R.id.input_kreatin);
        if(switch_gender.isChecked()) {
            genderCF = 1.018;
            genderAlpha = -0.329;
            genderKappa = 0.7;
        }
        else {
            genderCF = 1.0;
            genderAlpha = -0.411;
            genderKappa = 0.9;
        }
        if (switch_race.isChecked()) {
            racialCF = 1.159;
        }
        else racialCF = 1.0;

//        if (!input_age.getText().toString().isEmpty())
//            age = 30;
        age = Integer.parseInt(input_age.getText().toString());
//        if (!input_kreatin.getText().toString().isEmpty())
//            kreatinin = 0.22;
        kreatinin = Double.parseDouble(input_kreatin.getText().toString());
        result = 1*(kreatinin/genderKappa)*genderAlpha*genderCF*age;
    }
}