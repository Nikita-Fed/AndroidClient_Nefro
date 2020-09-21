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


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class In extends AppCompatActivity {

    private Button btnToReg, btnIn;
    private EditText email = null;
    private EditText password = null;
    public static String lgn = null, pwd = null;


    public void fncIn() { //Авторизация пользователя
        final OkHttpClient client = new OkHttpClient();
        String server = MainActivity.CONNECTION_URL;
//        String url = "http://192.168.1.114:8080/authorization";
        String url = server + "/authorization";
        //создаем  POST запрос для для входа
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("login", email.getText().toString())
                .addFormDataPart("password", password.getText().toString())
                .build();
        //
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
               /* In.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        System.out.println("Сработало");
                    }
                });*/
//                System.out.println(response.body().string());
                String strReturn = response.body().string();
                switch (strReturn) {
                    case ("ok_auth"):
                        Intent mainMenuAfteAuth = new Intent(".MainMenuAfteAuth");
                        startActivity(mainMenuAfteAuth);
                        lgn = email.getText().toString();
                        pwd = password.getText().toString();
                        System.out.println("Вы вошли в систему");
                        break;
                    default:
//                        Toast.makeText(In.this, "Вы ввели неверный e-mail или пароль", Toast.LENGTH_LONG).show();
                        /*runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast;
                                toast = Toast.makeText(In.this, "Вы ввели неверный e-mail или пароль", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });*/
                        System.out.println(strReturn);
                        lgn = null;
                        pwd = null;
                        break;
                }
            }
        });
    }

    public void fncToNextWindow() { // переход на окно регистрации или авторизация пользователя
        btnToReg = (Button) findViewById(R.id.btnToReg);
        btnIn = (Button) findViewById(R.id.btnIn);
        email = (EditText) findViewById(R.id.loginIn);
        password = (EditText) findViewById(R.id.passwordIn);
        btnToReg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Intent reg = new Intent(In.this, MainActivity.class);
                        Intent reg = new Intent(".Reg");
                        startActivity(reg);
                    }
                }
        );
        btnIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(email.getText().toString())) {
                            Toast.makeText(In.this, "Вы не ввели e-mail", Toast.LENGTH_LONG).show(); //Выводит ошибки в всплывающих окнах
                            return;
                        }
                        ;
                        if (password.getText().length() < 6) {
                            Toast.makeText(In.this, "Пароль должен быть минимум 6 символов", Toast.LENGTH_LONG).show();//Выводит ошибки в сплывающих окнах
                            return;
                        }
                        ;
                        fncIn();
                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);
        fncToNextWindow();
    }
}