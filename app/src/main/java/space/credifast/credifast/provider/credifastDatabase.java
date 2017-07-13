package space.credifast.credifast.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import space.credifast.credifast.R;
import space.credifast.credifast.interfaces.iTables;
import space.credifast.credifast.interfaces.iTbUsuColumns;

/**
 * Created by angel on 24/06/2017.
 */

public class credifastDatabase extends SQLiteOpenHelper implements iTables, iTbUsuColumns {
    
    public static Context context;
    
    public static final String TAG = "credifastDatabase";
    
    public static final String DATABASE_NAME = "credifastDB";

    public static final int DATABASE_VERSION = 1;

    public credifastDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Crea base de datos");
        db.beginTransaction();
        try{
            createTables(db);
            db.setTransactionSuccessful();
        }catch (Exception ex){
            Log.e("error", ex.toString());
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("actualiza", "vieja: " + oldVersion + " nueva: " + newVersion);
        db.beginTransaction();
        try {
            dropTables(db);
            createTables(db);
            db.setTransactionSuccessful();
        }catch (Exception ex){
            Log.e("error", ex.toString());
            db.endTransaction();
        }
    }

    private static void createTables(SQLiteDatabase db) {
        final StringBuilder stringBuilder = new StringBuilder();

        Log.i("information", "crea tabla tbusu");
        stringBuilder.append(context.getString(R.string.createTable))
                .append(tbUsu)
                .append("(")
                .append(fiIdUsu)
                .append(context.getString(R.string.enteroc))
                .append(fcUsu)
                .append(context.getString(R.string.textc))
                .append(fcCorreoElectronico)
                .append(context.getString(R.string.textc))
                .append(fnStatUsu)
                .append(context.getString(R.string.entero))
                .append(")");
        db.execSQL(stringBuilder.toString());
        stringBuilder.setLength(0);
    }

    private static void dropTables(SQLiteDatabase db) {
        try{
            Log.i("informacion", "Entra a borrar tablas");
            Log.i("information", "Borra tbUsu");
            db.beginTransaction();
            db.execSQL(context.getString(R.string.DropTable) + iTables.tbUsu);
            db.setTransactionSuccessful();
            db.endTransaction();

        }catch (Exception ex){
            Log.e("Error", ex.toString());
        }
    }
}
