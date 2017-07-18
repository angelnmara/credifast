package space.credifast.credifast.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import space.credifast.credifast.interfaces.iArticleColumns;
import space.credifast.credifast.interfaces.iMarcaColumns;
import space.credifast.credifast.interfaces.iTablas;
import space.credifast.credifast.interfaces.iUserColumns;
import space.credifast.credifast.interfaces.iVentaColumns;

/**
 * Created by Qualtop on 02/09/2016.
 */
public final class crediFastContract {
    public static final String CONTENT_AUTHORITY = "space.credifast.credifast.provider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    static final String PATH_USER = iTablas.USER;
    static final String PATH_ARTICLE = iTablas.ARTICLE;
    static final String PATH_VENTA = iTablas.VENTA;
    static final String PATH_MARCA = iTablas.MARCA;
    static final String PATH_VENTA_MARCA = iTablas.VENTA_MARCA;

    private crediFastContract(){}

    /*interface VentaMarcaColumns extends BaseColumns {
        public static final String VENTA_ID = "venta_id";
        public static final String VENTA_CODE = "venta_code";
        public static final String VENTA_NAME = "venta_name";
        public static final String VENTA_DESC = "venta_desc";
        public static final String VENTA_MARCA_ID = "venta_marca_id";
        public static final String VENTA_PRECIO = "venta_precio";
        public static final String VENTA_FOTO = "venta_foto";
        public static final String MARCA_ID = "marca_id";
        public static final String MARCA_NAME = "marca_name";
    }*/

    public static abstract class user implements iUserColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTablas.USER;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTablas.USER;
        public static final String DEFAULT_SORT = iUserColumns.NAME + " COLLATE NOCASE ASC";

        public static Uri buildUserUri(String UserId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getUserId(Uri uri){
            return uri.getLastPathSegment();
        }

    }

    public static abstract class venta implements iVentaColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_VENTA).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTablas.VENTA;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTablas.VENTA;
        public static final String DEFAULT_SORT = iVentaColumns.VENTA_NAME + " COLLATE NOCASE ASC";

        public static Uri buildVentaUri(String VentaId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getVentaId(Uri uri){
            return uri.getLastPathSegment();
        }

    }

    public static abstract class marca implements iMarcaColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MARCA).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTablas.MARCA;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTablas.MARCA;
        public static final String DEFAULT_SORT = iMarcaColumns.MARCA_NAME + " COLLATE NOCASE ASC";

        public static Uri buildMarcaUri(String VentaId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getMarcaId(Uri uri){
            return uri.getLastPathSegment();
        }
    }

    /*public static abstract class venta_marca implements VentaMarcaColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_VENTA_MARCA).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTablas.VENTA_MARCA;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTablas.VENTA_MARCA;
        public static final String DEFAULT_SORT = VentaMarcaColumns.VENTA_NAME + " COLLATE NOCASE ASC";

        public static Uri buildMarcaUri(String VentaId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getMarcaId(Uri uri){
            return uri.getLastPathSegment();
        }
    }*/

    public static abstract class article implements iArticleColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARTICLE).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTablas.ARTICLE;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + iTablas.ARTICLE;
        public static final String DEFAULT_SORT = iArticleColumns.ARTICLE_NAME + " COLLATE NOCASE ASC";

        public static Uri buildArticleUri(String ArticleId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getArticleId(Uri uri){
            return uri.getLastPathSegment();
        }

    }

}
