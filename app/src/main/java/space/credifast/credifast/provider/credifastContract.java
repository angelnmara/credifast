package space.credifast.credifast.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import space.credifast.credifast.interfaces.iArticleColumns;
import space.credifast.credifast.provider.crediFastDatabase.Tables;

/**
 * Created by Qualtop on 02/09/2016.
 */
public final class crediFastContract {
    public static final String CONTENT_AUTHORITY = "space.credifast.credifast.provider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    static final String PATH_USER = Tables.USER;
    static final String PATH_ARTICLE = Tables.ARTICLE;
    static final String PATH_VENTA = Tables.VENTA;
    static final String PATH_MARCA = Tables.MARCA;
    static final String PATH_VENTA_MARCA = Tables.VENTA_MARCA;

    private crediFastContract(){}

    interface UserColumns extends BaseColumns {
        public static final String MEMBER_NUMBER = "member_number";

        /** The Constant NAME. */
        public static final String NAME = "name";

        /** The Constant AGE. */
        public static final String AGE = "age";

        public static final String USER = "user";

        /** The Constant GENDER_ID. */
        public static final String GENDER_ID = "gender_id";

        /** The Constant GENDER. */
        public static final String GENDER = "gender";

        /** The Constant HEIGHT. */
        public static final String HEIGHT = "height";

        /** The Constant WEIGHT. */
        public static final String WEIGHT = "weight";

        /** The Constant REGISTRATION_DATE. */
        public static final String REGISTRATION_DATE = "registration_date";

        /** The Constant EMAIL. */
        public static final String EMAIL = "email";

        /** The Constant BIRTH_DATE. */
        public static final String BIRTH_DATE = "dob";

        /** The Constant MEMBER_TYPE. */
        public static final String MEMBER_TYPE = "member_type";

        public static final String USER_FB_ID = "user_fb_id";

        public static final String USER_FB_FIRST_NAME = "user_fb_first_name";

        public static final String USER_FB_LAST_NAME = "user_fb_last_name";

        public static final String USER_FB_BIRTHDAY = "user_fb_birthday";

        public static final String USER_FB_EMAIL = "user_fb_email";

        public static final String USER_FB_GENDER = "user_fb_gender";

        public static final String USER_FB_LOCALE = "user_fb_locale";

        public static final String LATITUD = "latitud";

        public static final String LONGITUD = "longitud";

    }

    /*public interface iArticleColumns extends BaseColumns {
        *//*public static final String ARTICLE_ID = "article_id";*//*
        public static final String ARTICLE_CODE = "article_code";
        public static final String ARTICLE_NAME = "article_name";
        public static final String ARTICLE_DESC = "article_desc";
        public static final String ARTICLE_MARCA_ID = "article_marca_id";
        public static final String ARTICLE_PRECIO = "article_precio";
        public static final String ARTICLE_COSTO = "article_costo";
        public static final String ARTICLE_FOTO = "article_foto";
        public static final String ARTICLE_STOCK = "article_stock";
    }*/

    public interface MarcaColumns extends BaseColumns {
        /*public static final String MARCA_ID = "marca_id";*/
        public static final String MARCA_NAME = "marca_name";
        public static final String MARCA_CODE = "marca_code";
        public static final String MARCA_IMAGEN = "marca_imagen";
        public static final String MARCA_OTRO1 = "marca_otro1";
        public static final String MARCA_OTRO2 = "marca_otro2";
    }

    public interface VentaColumns extends BaseColumns {
        public static final String VENTA_ID = "venta_id";
        public static final String VENTA_CODE = "venta_code";
        public static final String VENTA_NAME = "venta_name";
        public static final String VENTA_DESC = "venta_desc";
        public static final String VENTA_MARCA_ID = "venta_marca_id";
        public static final String VENTA_PRECIO = "venta_precio";
        public static final String VENTA_FOTO = "venta_foto";
    }
    interface VentaMarcaColumns extends BaseColumns {
        public static final String VENTA_ID = "venta_id";
        public static final String VENTA_CODE = "venta_code";
        public static final String VENTA_NAME = "venta_name";
        public static final String VENTA_DESC = "venta_desc";
        public static final String VENTA_MARCA_ID = "venta_marca_id";
        public static final String VENTA_PRECIO = "venta_precio";
        public static final String VENTA_FOTO = "venta_foto";
        public static final String MARCA_ID = "marca_id";
        public static final String MARCA_NAME = "marca_name";
    }

    interface TipoDato extends BaseColumns {
        public static final String TEXT_ = " TEXT COLLATE NOCASE,";
        public static final String TEXT = " TEXT COLLATE NOCASE";
        public static final String INT_KEY = " INTEGER PRIMARY KEY AUTOINCREMENT,";
        public static final String INT_ = " INTEGER,";
        public static final String INT = " INTEGER";
        public static final String BLOB_ = " BLOB,";
        public static final String BLOB = " BLOB";
        public static final String REAL_ = " REAL,";
        public static final String REAL = " REAL";
        public static final String DOUBLE_ = " REAL,";
        public static final String DOUBLE = " REAL";
        public static final String DATETIME_ = " NUMERIC,";
        public static final String DATETIME = " NUMERIC";
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
        public static final String PRIMARY_KEY_ = " PRIMARY KEY,";
        public static final String PRIMARY_KEY = " PRIMARY KEY";
    }

    public static abstract class user implements UserColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.USER;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.USER;
        public static final String DEFAULT_SORT = UserColumns.NAME + " COLLATE NOCASE ASC";

        public static Uri buildUserUri(String UserId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getUserId(Uri uri){
            return uri.getLastPathSegment();
        }

    }

    public static abstract class venta implements VentaColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_VENTA).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.VENTA;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.VENTA;
        public static final String DEFAULT_SORT = VentaColumns.VENTA_NAME + " COLLATE NOCASE ASC";

        public static Uri buildVentaUri(String VentaId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getVentaId(Uri uri){
            return uri.getLastPathSegment();
        }

    }

    public static abstract class marca implements MarcaColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MARCA).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.MARCA;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.MARCA;
        public static final String DEFAULT_SORT = MarcaColumns.MARCA_NAME + " COLLATE NOCASE ASC";

        public static Uri buildMarcaUri(String VentaId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getMarcaId(Uri uri){
            return uri.getLastPathSegment();
        }
    }

    public static abstract class venta_marca implements VentaMarcaColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_VENTA_MARCA).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.VENTA_MARCA;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.VENTA_MARCA;
        public static final String DEFAULT_SORT = VentaMarcaColumns.VENTA_NAME + " COLLATE NOCASE ASC";

        public static Uri buildMarcaUri(String VentaId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getMarcaId(Uri uri){
            return uri.getLastPathSegment();
        }
    }

    public static abstract class article implements iArticleColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARTICLE).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.ARTICLE;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.ARTICLE;
        public static final String DEFAULT_SORT = iArticleColumns.ARTICLE_NAME + " COLLATE NOCASE ASC";

        public static Uri buildArticleUri(String ArticleId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getArticleId(Uri uri){
            return uri.getLastPathSegment();
        }

    }

}
