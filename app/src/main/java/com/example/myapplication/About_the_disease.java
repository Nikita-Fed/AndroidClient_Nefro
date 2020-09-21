package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class About_the_disease extends AppCompatActivity {

    TextView someText;

    public void fnc() {// Загрузка статьи из БД
        String server = MainActivity.CONNECTION_URL;
        String url = server + "/articleFind";
        someText = (TextView) findViewById(R.id.about_the_disease);
        someText.setMovementMethod(new ScrollingMovementMethod());
        final OkHttpClient client = new OkHttpClient();
//        String url = "http://192.168.0.162:8080/articleFind";
        //создаем  POST запрос для для поиска статьи
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Почки - орган")
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
                About_the_disease.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Сработало");
                    }
                });
                String strReturn = response.body().string();
                someText.setText(strReturn);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_the_disease);
        fnc();
    }
}

        //---------------------------------------------------------


        /*someText.setText("Погиб поэт!- невольник чести —\n" +
                "Пал, оклеветанный молвой,\n" +
                "С свинцом в груди и жаждой мести,\n" +
                "Поникнув гордой головой!..\n" +
                "Не вынесла душа поэта\n" +
                "Позора мелочных обид,\n" +
                "Восстал он против мнений света\n" +
                "Один, как прежде… и убит!\n" +
                "Убит!.. К чему теперь рыданья,\n" +
                "Пустых похвал ненужный хор\n" +
                "И жалкий лепет оправданья?\n" +
                "Судьбы свершился приговор!\n" +
                "Не вы ль сперва так злобно гнали\n" +
                "Его свободный, смелый дар\n" +
                "И для потехи раздували\n" +
                "Чуть затаившийся пожар?\n" +
                "Что ж? веселитесь… Он мучений\n" +
                "Последних вынести не мог:\n" +
                "Угас, как светоч, дивный гений,\n" +
                "Увял торжественный венок.\n" +
                "\n" +
                "Его убийца хладнокровно\n" +
                "Навел удар… спасенья нет:\n" +
                "Пустое сердце бьется ровно,\n" +
                "В руке не дрогнул пистолет.\n" +
                "И что за диво?… издалека,\n" +
                "Подобный сотням беглецов,\n" +
                "На ловлю счастья и чинов\n" +
                "Заброшен к нам по воле рока;\n" +
                "Смеясь, он дерзко презирал\n" +
                "Земли чужой язык и нравы;\n" +
                "Не мог щадить он нашей славы;\n" +
                "Не мог понять в сей миг кровавый,\n" +
                "На что он руку поднимал!..\n" +
                "\n" +
                "И он убит — и взят могилой,\n" +
                "Как тот певец, неведомый, но милый,\n" +
                "Добыча ревности глухой,\n" +
                "Воспетый им с такою чудной силой,\n" +
                "Сраженный, как и он, безжалостной рукой.\n" +
                "\n" +
                "Зачем от мирных нег и дружбы простодушной\n" +
                "Вступил он в этот свет завистливый и душный\n" +
                "Для сердца вольного и пламенных страстей?\n" +
                "Зачем он руку дал клеветникам ничтожным,\n" +
                "Зачем поверил он словам и ласкам ложным,\n" +
                "Он, с юных лет постигнувший людей?..\n" +
                "\n" +
                "И прежний сняв венок — они венец терновый,\n" +
                "Увитый лаврами, надели на него:\n" +
                "Но иглы тайные сурово\n" +
                "Язвили славное чело;\n" +
                "Отравлены его последние мгновенья\n" +
                "Коварным шепотом насмешливых невежд,\n" +
                "И умер он — с напрасной жаждой мщенья,\n" +
                "С досадой тайною обманутых надежд.\n" +
                "Замолкли звуки чудных песен,\n" +
                "Не раздаваться им опять:\n" +
                "Приют певца угрюм и тесен,\n" +
                "И на устах его печать.");*/