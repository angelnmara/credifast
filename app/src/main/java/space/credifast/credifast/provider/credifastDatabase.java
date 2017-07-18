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
import space.credifast.credifast.interfaces.iMarcaColumns;
import space.credifast.credifast.interfaces.iTablas;
import space.credifast.credifast.provider.crediFastContract.TipoDato;
import space.credifast.credifast.provider.crediFastContract.UserColumns;
import space.credifast.credifast.provider.crediFastContract.VentaColumns;

/**
 * Created by Qualtop on 02/09/2016.
 */
public class crediFastDatabase extends SQLiteOpenHelper {

    public static final String TAG = "crediFastDatabase";

    public static final String DATABASE_NAME = "crediFastDB";

    private static final int DATABASE_VERSION = 4;

    public crediFastDatabase(Context context) {
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
            //db.delete(Tables.USER, null, null);
            db.execSQL("DROP TABLE IF EXISTS " + iTablas.USER);
            db.setTransactionSuccessful();
            db.endTransaction();

            db.beginTransaction();
            //db.delete(Tables.ARTICLE, null, null);
            db.execSQL("DROP TABLE IF EXISTS " + iTablas.ARTICLE);
            db.setTransactionSuccessful();
            db.endTransaction();

            db.beginTransaction();
            //db.delete(Tables.ARTICLE, null, null);
            db.execSQL("DROP TABLE IF EXISTS " + iTablas.VENTA);
            db.setTransactionSuccessful();
            db.endTransaction();

            db.beginTransaction();
            //db.delete(Tables.ARTICLE, null, null);
            db.execSQL("DROP TABLE IF EXISTS " + iTablas.MARCA);
            db.setTransactionSuccessful();
            db.endTransaction();

        }catch (Exception ex){
            Log.d(TAG, ex.getMessage());
        }
    }

    private static void createTables(SQLiteDatabase db){

        final StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("CREATE TABLE IF NOT EXISTS ").append(iTablas.USER)
                .append("(").append(UserColumns._ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(UserColumns.MEMBER_NUMBER).append(" INTEGER,")
                .append(UserColumns.NAME).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.AGE).append(" INTEGER,")
                .append(UserColumns.USER).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.GENDER_ID).append(" INTEGER,")
                .append(UserColumns.GENDER).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.HEIGHT).append(" REAL,")
                .append(UserColumns.WEIGHT).append(" REAL,")
                .append(UserColumns.REGISTRATION_DATE).append(" INTEGER,")
                .append(UserColumns.BIRTH_DATE).append(" INTEGER,")
                .append(UserColumns.MEMBER_TYPE)
                .append(" TEXT COLLATE NOCASE,")
                .append(" TEXT COLLATE NOCASE,").append(UserColumns.EMAIL)
                .append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_ID).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_FIRST_NAME).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_LAST_NAME).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_BIRTHDAY).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_EMAIL).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_GENDER).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_LOCALE).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.LATITUD).append(" REAL,")
                .append(UserColumns.LONGITUD).append(" REAL)");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);

        List<cCampos> lcCampos = new ArrayList<>();

        lcCampos.add(new cCampos(iArticleColumns._ID, true, "int"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_CODE, false, "string"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_NAME, false, "string"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_DESC, false, "string"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_MARCA_ID, false, "int"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_PRECIO, false, "decimal"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_COSTO, false, "decimal"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_FOTO, false, "blob"));
        lcCampos.add(new cCampos(iArticleColumns.ARTICLE_STOCK, false, "int"));

        cTablas ct = new cTablas(iTablas.ARTICLE, lcCampos);
        ct.queryCreaTabla();
        db.execSQL(ct.getStrBuilderTabla().toString());

        lcCampos = new ArrayList<>();

        lcCampos.add(new cCampos(iMarcaColumns._ID, true, "int"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_CODE, false, "int"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_NAME, false, "string"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_DESC, false, "string"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_IMAGEN, false, "blob"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_OTRO1, false, "string"));
        lcCampos.add(new cCampos(iMarcaColumns.MARCA_OTRO2, false, "string"));

        ct = new cTablas(iTablas.MARCA, lcCampos);
        ct.queryCreaTabla();
        db.execSQL(ct.getStrBuilderTabla().toString());

        /*strBuilder.append(TipoDato.CREATE_TABLE)
                .append(iTablas.MARCA)
                .append("(")
                .append(MarcaColumns._ID)
                .append(TipoDato.INT_KEY)
                .append(MarcaColumns.MARCA_CODE)
                .append(TipoDato.TEXT_)
                .append(MarcaColumns.MARCA_IMAGEN)
                .append(TipoDato.BLOB_)
                .append(MarcaColumns.MARCA_NAME)
                .append(TipoDato.TEXT_)
                .append(MarcaColumns.MARCA_OTRO1)
                .append(TipoDato.TEXT_)
                .append(MarcaColumns.MARCA_OTRO2)
                .append(TipoDato.TEXT)
                .append(")");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);*/

        strBuilder.append(TipoDato.CREATE_TABLE)
                .append(iTablas.VENTA)
                .append("(")
                .append(VentaColumns._ID)
                .append(TipoDato.INT_KEY)
                .append(VentaColumns.VENTA_CODE)
                .append(TipoDato.TEXT_)
                .append(VentaColumns.VENTA_NAME)
                .append(TipoDato.TEXT_)
                .append(VentaColumns.VENTA_DESC)
                .append(TipoDato.TEXT_)
                .append(VentaColumns.VENTA_MARCA_ID)
                .append(TipoDato.INT_)
                .append(VentaColumns.VENTA_PRECIO)
                .append(TipoDato.DOUBLE_)
                .append(VentaColumns.VENTA_FOTO)
                .append(TipoDato.BLOB)
                .append(")");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);

        strBuilder.append("PRAGMA foreign_keys = ON;");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);

    }

    /*interface Tables{
        static final String USER = "user";
        static final String ARTICLE = "article";
        static final String VENTA = "venta";
        static final String MARCA = "marca";
        static final String VENTA_MARCA = "venta_marca";
    }*/
}
