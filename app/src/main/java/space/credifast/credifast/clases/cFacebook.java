package space.credifast.credifast.clases;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import space.credifast.credifast.interfaces.iFacebook;
import space.credifast.credifast.interfaces.iFacebookUserColumns;
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

    GraphRequest graphRequest;
    AccessToken accessToken;
    String campos;
    Context context;

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
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
                            contentValues.put(iFacebookUserColumns.FACEBOOK_ID, object.getInt("id"));
                            contentValues.put(iFacebookUserColumns.FACEBOOK_NAME, object.getString("name"));
                            contentValues.put(iFacebookUserColumns.FACEBOOK_EMAIL, object.getString("email"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        final Uri uri = contentResolver.insert(facebook_user.CONTENT_URI, contentValues);
                        idFacebook = facebook_user.getFacebookUserId(uri).toString();
                    }
                }
        );
        Bundle params = new Bundle();
        params.putString("fields", campos);
        graphRequest.setParameters(params);
        graphRequest.executeAsync();
    }
}
