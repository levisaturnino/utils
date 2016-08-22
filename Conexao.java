package br.com.musicaaqui.musicaaqui.utils;

import android.util.Log;



import java.util.concurrent.TimeUnit;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * Created by saturnino on 17/06/16.
 */

public class Conexao {

    public  String conexao(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();

            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String conexaoVoto(String url,String parametro) {
        OkHttpClient client = new OkHttpClient();
             RequestBody formBody = new FormBody.Builder()
                .add("id", parametro)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .put(formBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();

            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String conexaoRegister(String url,String parametro) {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("token", parametro)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();
            Log.d("RESPOSTA",jsonString);
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
