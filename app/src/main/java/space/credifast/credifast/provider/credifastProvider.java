package space.credifast.credifast.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import space.credifast.credifast.R;
import space.credifast.credifast.interfaces.iTbUsuColumns;
import static space.credifast.credifast.interfaces.iTables.tbUsu;
import static space.credifast.credifast.provider.credifastContract.CONTENT_AUTHORITY;
import static space.credifast.credifast.provider.credifastContract.PATH_USER;
import static space.credifast.credifast.provider.credifastContract.cTbUsu;

/**
 * Created by angel on 24/06/2017.
 */

public class credifastProvider extends ContentProvider {

    public static final String TAG = "credifastProvider";

    private static final int CODE_ALL_USU = 1;
    private static final int CODE_SINGLE_USU = 2;

    private Context context;
    public static credifastDatabase mOpenHelper;
    private static final UriMatcher sUri = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        final String authority = CONTENT_AUTHORITY;
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(authority, PATH_USER, CODE_ALL_USU);
        matcher.addURI(authority, PATH_USER + "/#", CODE_SINGLE_USU);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        Log.d("on create", "provider create");
        context = getContext();
        mOpenHelper = new credifastDatabase(context);
        return true;
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        final int numOperaciones = operations.size();
        final ContentProviderResult[] results = new ContentProviderResult[numOperaciones];
        try{
            for(int i=0; i<numOperaciones; i++){
                results[i] = operations.get(i).apply(this, results, i);
            }
            db.setTransactionSuccessful();
        } catch (OperationApplicationException e) {
            Log.e("error", e.toString());
            e.printStackTrace();
            db.endTransaction();
        }
        return results;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final String Id;
        final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        final int match = sUri.match(uri);

        switch (match){
            case CODE_SINGLE_USU:
                Id = credifastContract.cTbUsu.getUsuId(uri);
                queryBuilder.appendWhere(iTbUsuColumns.fcUsu + " = " + Id);

            case CODE_ALL_USU:
                queryBuilder.setTables(tbUsu);
                break;

            default:
                Log.d(TAG, context.getString(R.string.opcionNoValida));
        }

        if (sortOrder == null){
            switch (match){
                case CODE_ALL_USU:
                    sortOrder = cTbUsu.DEFAULT_SORT;
                    break;
                default:
                    Log.d(TAG, context.getString(R.string.sinOrdenEspecial));
            }
        }else{
            Log.d(TAG, context.getString(R.string.sinOrdenEspecial));
        }

        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        Log.d(TAG, context.getString(R.string.terminaSelect));
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUri.match(uri);
        switch (match){
            case CODE_ALL_USU:
                return cTbUsu.CONTENT_TYPE;
            case CODE_SINGLE_USU:
                return cTbUsu.CONTENT_ITEM_TYPE;
            default:
                Log.d(TAG, context.getString(R.string.getTypeNoDefinido));
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri newUri = null;
        long rowId;
        final int match = sUri.match(uri);

        switch (match){
            case CODE_ALL_USU:
                rowId = db.insert(tbUsu, null, values);
                newUri = ContentUris.withAppendedId(cTbUsu.CONTENT_URI, rowId);
                break;
            default:
                Log.d(TAG, context.getString(R.string.tablaNoExiste));
        }

        getContext().getContentResolver().notifyChange(uri, null, false);

        return newUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUri.match(uri);
        int deleteRows = 0;
        final String id;
        switch (match){
            case CODE_SINGLE_USU:
                id = cTbUsu.getUsuId(uri);
                deleteRows = db.delete(tbUsu, iTbUsuColumns.fiIdUsu + "=?", new String[]{id});
                break;
            case CODE_ALL_USU:
                deleteRows = db.delete(tbUsu, selection, selectionArgs);
                break;
            default:
                Log.d(TAG, context.getString(R.string.tablaNoExiste));
        }
        getContext().getContentResolver().notifyChange(uri, null, false);

        return deleteRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUri.match(uri);
        final String id;
        int updateRow = 0;
        switch (match){
            case CODE_SINGLE_USU:
                id = cTbUsu.getUsuId(uri);
                updateRow = db.update(tbUsu, values, iTbUsuColumns.fiIdUsu + "=?", new String[]{id});
                break;
            case CODE_ALL_USU:
                updateRow = db.update(tbUsu, values, selection, selectionArgs);
                break;
            default:
                Log.d(TAG, context.getString(R.string.tablaNoExiste));
        }
        getContext().getContentResolver().notifyChange(uri, null, false);
        return updateRow;
    }

    @Nullable
    @Override
    public String[] getStreamTypes(@NonNull Uri uri, @NonNull String mimeTypeFilter) {
        return super.getStreamTypes(uri, mimeTypeFilter);
    }
}
