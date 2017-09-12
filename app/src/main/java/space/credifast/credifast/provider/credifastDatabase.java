package space.credifast.credifast.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import space.credifast.credifast.clases.cCampos;
import space.credifast.credifast.clases.cTablas;
import space.credifast.credifast.interfaces.iArticleColumns;
import space.credifast.credifast.interfaces.iFacebookUserColumns;
import space.credifast.credifast.interfaces.iMarcaColumns;
import space.credifast.credifast.interfaces.iTablas;
import space.credifast.credifast.interfaces.iUserColumns;
import space.credifast.credifast.interfaces.iVentaColumns;

/**
 * Created by Qualtop on 02/09/2016.
 */
public class credifastDatabase extends SQLiteOpenHelper {

    public static final String TAG = "crediFastDatabase";

    public static final String DATABASE_NAME = "crediFastDB";

    private static final int DATABASE_VERSION = 10;

    public credifastDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Crea base de datos");
        db.beginTransaction();
        try {
            createTables(db);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Log Entra a atualizar", " vieja" + oldVersion + " nueva" + newVersion);
        db.beginTransaction();
        try {
            dropTables(db);
            createTables(db);
            db.setTransactionSuccessful();
            Log.i("Log Termina a atualizar", " vieja" + oldVersion + " nueva" + newVersion);
        } finally {
            db.endTransaction();
        }
    }

    public static void dropTables(SQLiteDatabase db) {
        try {

            Log.d(TAG, "Elimina");

            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + iTablas.USER);
            db.setTransactionSuccessful();
            db.endTransaction();

            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + iTablas.ARTICLE);
            db.setTransactionSuccessful();
            db.endTransaction();

            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + iTablas.VENTA);
            db.setTransactionSuccessful();
            db.endTransaction();

            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + iTablas.MARCA);
            db.setTransactionSuccessful();
            db.endTransaction();

            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + iTablas.FACEBOOK_USER);
            db.setTransactionSuccessful();
            db.endTransaction();

        }catch (Exception ex){
            Log.d(TAG, ex.getMessage());
        }
    }

    private static void createTables(SQLiteDatabase db){

        final StringBuilder strBuilder = new StringBuilder();

        List<cCampos> lcCampos = new ArrayList<>();

        lcCampos.add(new cCampos(iUserColumns._ID, true, "int"));
        lcCampos.add(new cCampos(iUserColumns.MEMBER_NUMBER, false, "int"));
        lcCampos.add(new cCampos(iUserColumns.NAME, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.AGE, false, "int"));
        lcCampos.add(new cCampos(iUserColumns.USER, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.GENDER_ID, false, "int"));
        lcCampos.add(new cCampos(iUserColumns.GENDER, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.HEIGHT, false, "decimal"));
        lcCampos.add(new cCampos(iUserColumns.WEIGHT, false, "decimal"));
        lcCampos.add(new cCampos(iUserColumns.REGISTRATION_DATE, false, "date"));
        lcCampos.add(new cCampos(iUserColumns.BIRTH_DATE, false, "date"));
        lcCampos.add(new cCampos(iUserColumns.MEMBER_TYPE, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.EMAIL, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.USER_FB_ID, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.USER_FB_FIRST_NAME, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.USER_FB_LAST_NAME, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.USER_FB_BIRTHDAY, false, "date"));
        lcCampos.add(new cCampos(iUserColumns.USER_FB_EMAIL, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.USER_FB_GENDER, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.USER_FB_LOCALE, false, "string"));
        lcCampos.add(new cCampos(iUserColumns.LATITUD, false, "decimal"));
        lcCampos.add(new cCampos(iUserColumns.LONGITUD, false, "decimal"));

        cTablas ct = new cTablas(iTablas.USER, lcCampos);
        ct.queryCreaTabla();
        db.execSQL(ct.getStrBuilderTabla().toString());

        lcCampos = new ArrayList<>();

        lcCampos.add(new cCampos(iArticleColumns._ID, true, "int"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_CODE, false, "string"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_NAME, false, "string"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_DESC, false, "string"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_MARCA_ID, false, "int"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_PRECIO, false, "decimal"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_COSTO, false, "decimal"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_FOTO, false, "blob"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_STOCK, false, "int"));

        ct = new cTablas(iTablas.ARTICLE, lcCampos);
        ct.queryCreaTabla();
        db.execSQL(ct.getStrBuilderTabla().toString());

        lcCampos = new ArrayList<>();

        lcCampos.add(new cCampos(iMarcaColumns._ID, true, "int"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_CODE, false, "string"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_NAME, false, "string"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_DESC, false, "string"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_IMAGEN, false, "blob"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_OTRO1, false, "string"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_OTRO2, false, "string"));

        ct = new cTablas(iTablas.MARCA, lcCampos);
        ct.queryCreaTabla();
        db.execSQL(ct.getStrBuilderTabla().toString());

        lcCampos = new ArrayList<>();

        lcCampos.add(new cCampos(iVentaColumns._ID, true, "int"));
        lcCampos.add(new cCampos(iVentaColumns.VENTA_CODE, false, "string"));
        lcCampos.add(new cCampos(iVentaColumns.VENTA_NAME, false, "string"));
        lcCampos.add(new cCampos(iVentaColumns.VENTA_DESC, false, "string"));
        lcCampos.add(new cCampos(iVentaColumns.VENTA_MARCA_ID, false, "int"));
        lcCampos.add(new cCampos(iVentaColumns.VENTA_PRECIO, false, "decimal"));
        lcCampos.add(new cCampos(iVentaColumns.VENTA_FOTO, false, "blob"));

        ct = new cTablas(iTablas.VENTA, lcCampos);
        ct.queryCreaTabla();
        db.execSQL(ct.getStrBuilderTabla().toString());

        lcCampos = new ArrayList<>();
        lcCampos.add(new cCampos(iFacebookUserColumns._ID, true, "int"));
        lcCampos.add(new cCampos(iFacebookUserColumns.FACEBOOK_ID, false, "int"));
        lcCampos.add(new cCampos(iFacebookUserColumns.FACEBOOK_NAME, false, "string"));
        lcCampos.add(new cCampos(iFacebookUserColumns.FACEBOOK_EMAIL, false, "string"));
        lcCampos.add(new cCampos(iFacebookUserColumns.FACEBOOK_FOTO_PERFIL_URL, false, "string"));

        ct = new cTablas(iTablas.FACEBOOK_USER, lcCampos);
        ct.queryCreaTabla();
        db.execSQL(ct.getStrBuilderTabla().toString());

        /*  Investigar  */
        strBuilder.append("PRAGMA foreign_keys = ON;");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);

    }
}
