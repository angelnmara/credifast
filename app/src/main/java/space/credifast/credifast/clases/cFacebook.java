package space.credifast.credifast.clases;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import space.credifast.credifast.MainActivity;
import space.credifast.credifast.interfaces.iFacebook;
import space.credifast.credifast.interfaces.iFacebookUserColumns;
import space.credifast.credifast.provider.credifastContract.facebook_user;


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
    String idFacebook;

    cUsuarioFB cu = new cUsuarioFB();

    @Override
    public void getMe() {
        graphRequest = GraphRequest.newMeRequest(
                accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        final ContentResolver contentResolver = getContext().getContentResolver();
                        final ContentValues contentValues = new ContentValues();
                        try {
                            String IdF = object.has("id")?object.getString("id"):"";
                            String nameF = object.has("name")?object.getString("name"):"";
                            String emailF = object.has("email")?object.getString("email"):"";
                            String fotoPerfilF = object.has("picture")?object.getJSONObject("picture").getJSONObject("data").getString("url"):"";

                            cu.getUsuarioFB(getContext(), IdF);

                            if(!cu.isExiste()){
                                contentValues.put(iFacebookUserColumns.FACEBOOK_ID, IdF);
                                contentValues.put(iFacebookUserColumns.FACEBOOK_NAME, nameF);
                                contentValues.put(iFacebookUserColumns.FACEBOOK_EMAIL, emailF);
                                contentValues.put(iFacebookUserColumns.FACEBOOK_FOTO_PERFIL_URL, fotoPerfilF);
                                final Uri uri = contentResolver.insert(facebook_user.CONTENT_URI, contentValues);
                                idFacebook = facebook_user.getFacebookUserId(uri).toString();
                                Log.d(TAG, "Usuario se dio de alta con id = " + idFacebook);
                            }
                            else{
                                Log.d(TAG, "Usuario existente");
                            }
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            context.startActivity(intent);
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

    @Override
    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    @Override
    public String getUserId(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken.getUserId();
    }


}
