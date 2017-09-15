package space.credifast.credifast.clases;

import android.content.Context;
import android.database.Cursor;

import space.credifast.credifast.interfaces.iFacebookUserColumns;
import space.credifast.credifast.provider.credifastContract;

import static java.security.AccessController.getContext;
import static space.credifast.credifast.interfaces.iFacebookUserColumns.FACEBOOK_EMAIL;
import static space.credifast.credifast.interfaces.iFacebookUserColumns.FACEBOOK_NAME;

/**
 * Created by root on 13/09/17.
 */

public class cUsuarioFB {

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getFbEmail() {
        return fbEmail;
    }

    public void setFbEmail(String fbEmail) {
        this.fbEmail = fbEmail;
    }

    public String getFbName() {
        return fbName;
    }

    public void setFbName(String fbName) {
        this.fbName = fbName;
    }

    private String fbName;
    private String fbEmail;
    private boolean existe = false;

    public void getUsuarioFB(Context ctx, String id){
        String[] projection = new String[]{iFacebookUserColumns.FACEBOOK_ID, iFacebookUserColumns.FACEBOOK_NAME, FACEBOOK_EMAIL, iFacebookUserColumns.FACEBOOK_FOTO_PERFIL_URL};
        String where = iFacebookUserColumns.FACEBOOK_ID +  "=?";
        String[] vals = new String[]{id};
        Cursor facebookUserCursor = ctx.getContentResolver().query(credifastContract.facebook_user.CONTENT_URI, projection, where, vals, null);
        /*if(facebookUserCursor.getCount()>0){
            existe=true;
        }*/
        if(facebookUserCursor.moveToFirst()==true){
            existe = true;
            int fbEmailIndex = facebookUserCursor.getColumnIndex(FACEBOOK_EMAIL);
            fbEmail = facebookUserCursor.getString(fbEmailIndex);
            int fbNameIndex = facebookUserCursor.getColumnIndex(FACEBOOK_NAME);
            fbName = facebookUserCursor.getString(fbNameIndex);
            facebookUserCursor.close();
        }
    }
}
