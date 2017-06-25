package space.credifast.credifast.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import space.credifast.credifast.R;
import space.credifast.credifast.interfaces.iTables;
import space.credifast.credifast.interfaces.iTbUsuColumns;

/**
 * Created by angel on 24/06/2017.
 */

public final class credifastContract {

    private static Context context;

    public static final String CONTENT_AUTHORITY = context.getString(R.string.contentAutority);

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    static final String PATH_USER = iTables.iTbUsu;

    private credifastContract(){}

    public static abstract class cTbUsu implements iTbUsuColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTables.iTbUsu;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTables.iTbUsu;
        public static final String DEFAULT_SORT = iTbUsuColumns.fcUsu + " COLLATE NOCASE ASC";

        public static Uri buildTbUsuUri(String fiIdUsu){
            return CONTENT_URI.buildUpon().appendPath(iTbUsuColumns.fiIdUsu).build();
        }

        public static String getUsuId(Uri uri){return uri.getLastPathSegment();}
    }

}
