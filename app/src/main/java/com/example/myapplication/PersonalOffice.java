package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonalOffice extends AppCompatActivity {

    private Button btnSave;
    private TextView textSex, firstName, secondName;
    private Number numbAge;
    private Switch switchSex;

    public void fncRegNewUser() {//Обновление данных пользователя
        final OkHttpClient client = new OkHttpClient();
        String server = MainActivity.CONNECTION_URL;
        String url = server + "/personalOffice";
        final MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("login", In.lgn);
            postdata.put("password", In.pwd);
            postdata.put("firstName", firstName.getText().toString());
            postdata.put("secondName", secondName.getText().toString());
            postdata.put("sex", textSex.getText().toString());
        } catch (
                JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PersonalOffice.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Сработало");
                    }
                });
                System.out.println(response.body().string());
            }
        });
    }

    public void fncChooseSex() {
        textSex = (TextView) findViewById(R.id.textSex);
        switchSex = (Switch) findViewById(R.id.switchSex);
//        firstName = (TextView) findViewById(R.id.firstName);
        switchSex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    textSex.setText("Женщина");
                } else textSex.setText("Мужчина");
            }
        });
    }

    public void fncToNextWindow() {
        btnSave = (Button) findViewById(R.id.btnSave);
        firstName = (TextView) findViewById(R.id.firstName);
        secondName = (TextView) findViewById(R.id.secondName);
//        numbAge = (NumberFormat) findViewById(R.id.numbAge);
        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (firstName.getText().toString().length() > 0 && secondName.getText().toString().length() > 0) {
                            fncRegNewUser(); // обновление данных
                        } else {
                            Toast.makeText(PersonalOffice.this, "Вы заполнили не все поля", Toast.LENGTH_LONG).show();//Выводит ошибки в сплывающих окнах
                            System.out.println("Ошибка");
                            return;
                        }
                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_office);
        fncToNextWindow();
        fncChooseSex();
    }
}