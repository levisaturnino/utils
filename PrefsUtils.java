package br.com.musicaaqui.musicaaqui.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ricardo on 31/12/14.
 */
public class PrefsUtils {
     private static final String PREF_ID = "acheigranderecife";



    public static void setInteger(Context context, String chave, int valor){
        SharedPreferences  prefs = context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(chave, valor);
        editor.commit();
    }

    public static int getInteger(Context context, String chave){
        SharedPreferences  prefs = context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE);
        int i = prefs.getInt(chave,0);
        return i;
    }


    public static void setString(Context context, String chave, String valor){
        SharedPreferences  prefs = context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(chave, valor);
        editor.commit();
    }

    public static String getString(Context context, String chave){
        SharedPreferences  prefs = context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE);
        String s = prefs.getString(chave,"");
       return s;
    }
}
