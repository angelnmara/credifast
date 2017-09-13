package space.credifast.credifast.clases;

import android.content.Context;
import android.database.Cursor;

import space.credifast.credifast.interfaces.iFacebookUserColumns;
import space.credifast.credifast.provider.credifastContract;

import static java.security.AccessController.getContext;

/**
 * Created by root on 13/09/17.
 */

public class cUsuario {

    boolean fnVerificaExisteUsuarioFB(Context ctx, String id){
        boolean existe = false;
        String[] projection = new String[]{iFacebookUserColumns.FACEBOOK_ID, iFacebookUserColumns.FACEBOOK_NAME, iFacebookUserColumns.FACEBOOK_EMAIL, iFacebookUserColumns.FACEBOOK_FOTO_PERFIL_URL};
        String where = iFacebookUserColumns.FACEBOOK_ID +  "=?";
        String[] vals = new String[]{id};
        Cursor facebookUserCursor = ctx.getContentResolver().query(credifastContract.facebook_user.CONTENT_URI, projection, where, vals, null);
        if(facebookUserCursor.getCount()>0){
            existe=true;
        }
        return existe;
    }

}
