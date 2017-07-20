package space.credifast.credifast.clases;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioFocusRequest;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import space.credifast.credifast.interfaces.iFacebook;
import space.credifast.credifast.interfaces.iFacebookUserColumns;
import space.credifast.credifast.provider.crediFastContract;
import space.credifast.credifast.provider.crediFastContract.facebook_user;

/**
 * Created by angel on 23/06/2017.
 */

public class cFacebook implements iFacebook {

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void setCampos(String campos) {
        this.campos = campos;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private final String TAG = "cFacebook";

    GraphRequest graphRequest;
    AccessToken accessToken;
    String campos;
    Context context;

    public String getIdFacebook() {
        return idFacebook;
    }

    String idFacebook;

    @Override
    public void getMe() {
        graphRequest = GraphRequest.newMeRequest(
                accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        final ContentResolver contentResolver = getContext().getContentResolver();
                        final ContentValues contentValues = new ContentValues();


                        try {
                            if(!fnVerificaUsuario(object.getString("id"))){

                                String IdF = object.has("id")?object.getString("id"):"";
                                contentValues.put(iFacebookUserColumns.FACEBOOK_ID, IdF);

                                String nameF = object.has("name")?object.getString("name"):"";
                                contentValues.put(iFacebookUserColumns.FACEBOOK_NAME, nameF);

                                String emailF = object.has("email")?object.getString("email"):"";
                                contentValues.put(iFacebookUserColumns.FACEBOOK_EMAIL, emailF);

                                final Uri uri = contentResolver.insert(facebook_user.CONTENT_URI, contentValues);
                                idFacebook = facebook_user.getFacebookUserId(uri).toString();
                                Log.d(TAG, "Usuario se dio de alta con id = " + idFacebook);
                            }
                            else{
                                Log.d(TAG, "Usuario existente");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
        Bundle params = new Bundle();
        params.putString("fields", campos);
        graphRequest.setParameters(params);
        graphRequest.executeAsync();
    }

    private boolean fnVerificaUsuario(String id){
        boolean existe = false;
        String[] projection = new String[]{iFacebookUserColumns.FACEBOOK_ID, iFacebookUserColumns.FACEBOOK_NAME, iFacebookUserColumns.FACEBOOK_EMAIL};
        String where = iFacebookUserColumns.FACEBOOK_ID +  "=?";
        String[] vals = new String[]{id};
        Cursor facebookUserCursor = getContext().getContentResolver().query(facebook_user.CONTENT_URI, projection, where, vals, null);
        if(facebookUserCursor.getCount()>0){
            existe=true;
        }
        return existe;
    }
}
