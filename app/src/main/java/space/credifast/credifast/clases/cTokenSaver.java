package space.credifast.credifast.clases;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Qualtop on 13/03/2018.
 */

public class cTokenSaver {
    private final static String SHARED_PREF_NAME = "space.credifast.credifast.SHARED_PREF_NAME";
    private final static String TOKEN_KEY = "space.credifast.credifast.TOKEN_KEY";
    private final static String idSucursal_KEY = "space.credifast.credifast.idSucursal_KEY";
    private final static String idPelicula_KEY = "space.credifast.credifast.idPelicula_KEY";

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

    public static int getIdSucursal(Context c){
        SharedPreferences preferences = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(idSucursal_KEY, 0);
    }

    public static void setIdSucursal(Context c, int idSucursal){
        SharedPreferences preferences = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(idSucursal_KEY, idSucursal);
        editor.apply();
    }

    public static int getIdPelicula(Context c){
        SharedPreferences preferences = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(idPelicula_KEY, 0);
    }

    public static void setIdPelicula(Context c, int idPelicula){
        SharedPreferences preferences = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(idPelicula_KEY, idPelicula);
        editor.apply();
    }

}