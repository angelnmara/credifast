package space.credifast.credifast.clases;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Qualtop on 13/03/2018.
 */

public class cTokenSaver {
    private final static String SHARED_PREF_NAME = "space.credifast.credifast.SHARED_PREF_NAME";
    private final static String TOKEN_KEY = "space.credifast.credifast.TOKEN_KEY";

    public static String getToken(Context c){
        SharedPreferences preferences = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(TOKEN_KEY, "");
    }

    public static void setToken(Context c, String token){
        SharedPreferences preferences = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

}