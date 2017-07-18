package space.credifast.credifast.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

//import space.credifast.credifast.provider.crediFastContract.ArticleColumns;
import space.credifast.credifast.interfaces.iArticleColumns;
import space.credifast.credifast.interfaces.iMarcaColumns;
import space.credifast.credifast.interfaces.iTablas;
import space.credifast.credifast.interfaces.iUserColumns;
import space.credifast.credifast.interfaces.iVentaColumns;
import space.credifast.credifast.provider.crediFastContract.article;
import space.credifast.credifast.provider.crediFastContract.marca;
import space.credifast.credifast.provider.crediFastContract.user;
import space.credifast.credifast.provider.crediFastContract.venta;

import java.util.ArrayList;

/**
 * Created by Qualtop on 05/09/2016.
 */
public class crediFastProvider extends ContentProvider {

    private static final String TAG = "crediFastProvider";

    private static final int CODE_ALL_USERS = 1;
    private static final int CODE_SINGLE_USER = 2;

    private static final int CODE_ALL_ARTICLES = 3;
    private static final int CODE_SINGLE_ARTICLE = 4;

    private static final int CODE_ALL_VENTA = 5;
    private static final int CODE_SINGLE_VENTA = 6;

    private static final int CODE_ALL_MARCA = 7;
    private static final int CODE_SINGLE_MARCA = 8;

    private static final int CODE_ALL_VENTA_MARCA = 9;
    private static final int CODE_SINGLE_VENTA_MARCA = 10;

    private Context context;
    private static crediFastDatabase mOpenHelper;
    private static final UriMatcher sUri = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        final String authority = crediFastContract.CONTENT_AUTHORITY;
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(authority, crediFastContract.PATH_USER, CODE_ALL_USERS);
        matcher.addURI(authority, crediFastContract.PATH_USER + "/#", CODE_SINGLE_USER);

        matcher.addURI(authority, crediFastContract.PATH_ARTICLE, CODE_ALL_ARTICLES);
        matcher.addURI(authority, crediFastContract.PATH_ARTICLE + "/#", CODE_SINGLE_ARTICLE);

        matcher.addURI(authority, crediFastContract.PATH_VENTA, CODE_ALL_VENTA);
        matcher.addURI(authority, crediFastContract.PATH_VENTA + "/#", CODE_SINGLE_VENTA);

        matcher.addURI(authority, crediFastContract.PATH_MARCA, CODE_ALL_MARCA);
        matcher.addURI(authority, crediFastContract.PATH_MARCA + "/#", CODE_SINGLE_MARCA);

        matcher.addURI(authority, crediFastContract.PATH_VENTA_MARCA, CODE_ALL_VENTA_MARCA);
        matcher.addURI(authority, crediFastContract.PATH_VENTA_MARCA + "/#", CODE_SINGLE_VENTA_MARCA);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        context = getContext();
        mOpenHelper = new crediFastDatabase(context);
        return true;
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        final int numOperations = operations.size();
        final ContentProviderResult[] results = new ContentProviderResult[numOperations];
        try {
            for(int i = 0; i < numOperations; i++){
                results[i] = operations.get(i).apply(this, results, i);
            }
            db.setTransactionSuccessful();
        }catch (Exception ex){
            Log.d(TAG, "Error ContentPrpviderResult");
            db.endTransaction();
        }
        return results;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final String Id;
        final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        final int match = sUri.match(uri);

        switch (match){
            case CODE_SINGLE_USER:
                Id = user.getUserId(uri);
                queryBuilder.appendWhere(iUserColumns._ID + " = " + Id);

            case CODE_ALL_USERS:
                queryBuilder.setTables(iTablas.USER);
                break;

            case CODE_SINGLE_ARTICLE:
                Id = article.getArticleId(uri);
                queryBuilder.appendWhere(iArticleColumns._ID + " = " + Id);

            case CODE_ALL_ARTICLES:
                queryBuilder.setTables(iTablas.ARTICLE);
                break;

            case CODE_SINGLE_VENTA:
                Id = venta.getVentaId(uri);
                queryBuilder.appendWhere(iVentaColumns._ID + "=" + Id);
                break;

            case CODE_ALL_VENTA:
                queryBuilder.setTables(iTablas.VENTA);
                break;

            case CODE_SINGLE_MARCA:
                Id = marca.getMarcaId(uri);
                queryBuilder.appendWhere(iMarcaColumns._ID + "=" + Id);
                break;

            case CODE_ALL_MARCA:
                queryBuilder.setTables(iTablas.MARCA);
                break;

            /*case CODE_ALL_VENTA_MARCA:
                queryBuilder.setTables("venta inner join marca on venta.venta_marca_id = marca._id");
                break;

            case CODE_SINGLE_VENTA_MARCA:
                Id = venta_marca.getMarcaId(uri);
                queryBuilder.appendWhere(VentaMarcaColumns._ID + "=" + Id);
                break;*/

            default:
                Log.d(TAG, "Opcion no valida");
        }

        if (sortOrder == null){

            switch (match){
                case CODE_ALL_USERS:
                    sortOrder = user.DEFAULT_SORT;
                    break;

                case CODE_ALL_ARTICLES:
                    sortOrder = article.DEFAULT_SORT;
                    break;

                case CODE_ALL_VENTA:
                    sortOrder = venta.DEFAULT_SORT;
                    break;

                case CODE_ALL_MARCA:
                    sortOrder = marca.DEFAULT_SORT;
                    break;

                case CODE_ALL_VENTA_MARCA:
                    sortOrder = marca.DEFAULT_SORT;
                    break;
            }

        }else{
            Log.d(TAG, "Sin orden registrado");
        }

        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        Log.d(TAG, "Termina el select");
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUri.match(uri);
        switch (match){
            case CODE_ALL_USERS:
                return user.CONTENT_TYPE;

            case CODE_SINGLE_USER:
                return user.CONTENT_ITEM_TYPE;

            case CODE_ALL_ARTICLES:
                return article.CONTENT_TYPE;

            case CODE_SINGLE_ARTICLE:
                return article.CONTENT_ITEM_TYPE;

            case CODE_ALL_VENTA:
                return venta.CONTENT_TYPE;

            case CODE_SINGLE_VENTA:
                return venta.CONTENT_ITEM_TYPE;

            case CODE_ALL_MARCA:
                return marca.CONTENT_TYPE;

            case CODE_SINGLE_MARCA:
                return marca.CONTENT_ITEM_TYPE;

            default:
                Log.d(TAG, "getType no definido");
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri newUri = null;
        long rowId;
        final int match = sUri.match(uri);

        switch (match){
            case CODE_ALL_USERS:
                //crediFastDatabase.dropTables(db);
                rowId = db.insert(iTablas.USER, null, values);
                newUri = ContentUris.withAppendedId(user.CONTENT_URI, rowId);
                break;

            case CODE_ALL_ARTICLES:
                rowId = db.insert(iTablas.ARTICLE, null, values);
                newUri = ContentUris.withAppendedId(article.CONTENT_URI, rowId);
                break;

            case CODE_ALL_VENTA:
                rowId = db.insert(iTablas.VENTA, null, values);
                newUri = ContentUris.withAppendedId(venta.CONTENT_URI, rowId);
                break;

            case CODE_ALL_MARCA:
                rowId = db.insert(iTablas.MARCA, null, values);
                newUri = ContentUris.withAppendedId(marca.CONTENT_URI, rowId);
                break;

            default:
                Log.d(TAG, "Tabla no existe");
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null, false);

        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUri.match(uri);
        int deleteRows = 0;
        final String id;

        switch (match){
            case CODE_SINGLE_USER:
                id = user.getUserId(uri);
                deleteRows = db.delete(iTablas.USER, iUserColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_USERS:
                deleteRows = db.delete(iTablas.USER, selection, selectionArgs);
                break;

            case CODE_SINGLE_ARTICLE:
                id = article.getArticleId(uri);
                deleteRows = db.delete(iTablas.ARTICLE, iArticleColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_ARTICLES:
                deleteRows = db.delete(iTablas.ARTICLE, selection, selectionArgs);
                break;

            case CODE_SINGLE_VENTA:
                id = venta.getVentaId(uri);
                deleteRows = db.delete(iTablas.VENTA, iVentaColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_VENTA:
                deleteRows = db.delete(iTablas.VENTA, selection, selectionArgs);
                break;

            case CODE_SINGLE_MARCA:
                id = marca.getMarcaId(uri);
                deleteRows = db.delete(iTablas.MARCA, iMarcaColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_MARCA:
                deleteRows = db.delete(iTablas.MARCA, selection, selectionArgs);
                break;

            default:
                Log.d(TAG, "No existe tabla para borrar");
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null, false);

        return deleteRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUri.match(uri);
        final String id;
        int updateRows = 0;

        switch (match){
            case CODE_SINGLE_USER:
                id = user.getUserId(uri);
                updateRows = db.update(iTablas.USER, values, iUserColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_USERS:
                updateRows = db.update(iTablas.USER, values, selection, selectionArgs);
                break;

            case CODE_SINGLE_ARTICLE:
                id = article.getArticleId(uri);
                updateRows = db.update(iTablas.ARTICLE, values, iArticleColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_ARTICLES:
                updateRows = db.update(iTablas.ARTICLE, values, selection, selectionArgs);
                break;

            case CODE_SINGLE_VENTA:
                id = venta.getVentaId(uri);
                updateRows = db.update(iTablas.VENTA, values, iVentaColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_VENTA:
                updateRows = db.update(iTablas.VENTA, values, selection, selectionArgs);
                break;

            case CODE_SINGLE_MARCA:
                id = marca.getMarcaId(uri);
                updateRows = db.update(iTablas.MARCA, values, iMarcaColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_MARCA:
                updateRows = db.update(iTablas.MARCA, values, selection, selectionArgs);
                break;

            default:
                Log.d(TAG, "update no registrado");
        }

        getContext().getContentResolver().notifyChange(uri, null, false);

        return updateRows;
    }
}
