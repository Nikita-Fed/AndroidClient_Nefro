package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Reg extends AppCompatActivity {

    private Button btn2, btnAddPeople;
    private EditText firstName, secondName, email = null;
    private EditText password = null;
    private String emailForCheck;
    public static String lgn = null, pwd = null;

    public void fncRegNewUser() {//Регистрация нового пользователя
        final OkHttpClient client = new OkHttpClient();
        String server = MainActivity.CONNECTION_URL;
//        String url = "http://192.168.1.114:8080/registration";
        String url = server + "/registration";
        final MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("login", email.getText().toString());
            postdata.put("password", password.getText().toString());
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
                /*Reg.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Сработало");
                    }
                });*/
                String strReturn = response.body().string();
                switch (strReturn) {
                    case ("ok_reg"):
                        Intent mainMenuAfteAuth = new Intent(".MainMenuAfteAuth");
                        startActivity(mainMenuAfteAuth);
                        In.lgn = email.getText().toString();
                        In.pwd = password.getText().toString();
                        System.out.println("Вы вошли в систему");
                        break;
                    default:
                        System.out.println(strReturn);
                        lgn = null;
                        pwd = null;
                        break;
                }
//                System.out.println(response.body().string());
            }
        });
    }

    public void fncdb() {//Проверка условий для логина/пароля, затем - Регистрация нового пользователя
        password = (EditText) findViewById(R.id.PasswordForInUser);
        email = (EditText) findViewById(R.id.emailForRegByUser);
        btnAddPeople = (Button) findViewById(R.id.btnAddPeople);
        btnAddPeople.setOnClickListener(new View.OnClickListener() { //При нажатии книпки "Зарегестрироваться"

            @Override
            public void onClick(View view) {//Нажатие кнопки регистрации
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(Reg.this, "Вы не ввели e-mail", Toast.LENGTH_LONG).show();//Выводит ошибки в сплывающих окнах
                    return;
                }
                ;
                if (password.getText().length() < 6) {
                    Toast.makeText(Reg.this, "Пароль должен быть длиннее 6 символов", Toast.LENGTH_LONG).show();//Выводит ошибки в сплывающих окнах
                    return;
                }
                ;
                fncRegNewUser();//регистрация нового пользователя
            }
        });
    }

    public void fncToNextWindow() { //переход на окно Авторизации
        btn2 = (Button) findViewById(R.id.btnToIn);
        btn2.setOnClickListener( //При нажатии книпки "Войти"
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent reg = new Intent(".In");
                        startActivity(reg);
                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        fncToNextWindow();//Переход на другое окно приложения "Авторизации"
        fncdb();//Добавление пользователя в БД
    }
}