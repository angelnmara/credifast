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

import space.credifast.credifast.interfaces.iFacebook;
import space.credifast.credifast.interfaces.iTbUsuColumns;
import space.credifast.credifast.provider.credifastContract;
import space.credifast.credifast.provider.credifastContract.cTbUsu;
import space.credifast.credifast.dialog.genericDilog;

import static com.facebook.AccessToken.getCurrentAccessToken;

/**
 * Created by angel on 23/06/2017.
 */

public class cFacebook implements iFacebook {

    public GraphResponse getGraphResponse() {
        return graphResponse;
    }

    public void setGraphResponse(GraphResponse graphResponse) {
        this.graphResponse = graphResponse;
    }

    public GraphRequest getGraphRequest() {
        return graphRequest;
    }

    public void setGraphRequest(GraphRequest graphRequest) {
        this.graphRequest = graphRequest;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public String getCampos() {
        return campos;
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

    GraphResponse graphResponse;
    GraphRequest graphRequest;
    JSONObject jsonObject;
    AccessToken accessToken;
    String campos;
    Context context;

    @Override
    public void getMe() {
        graphRequest = GraphRequest.newMeRequest(
                accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        jsonObject = object;
                        graphResponse = response;
                        //Log.d("Error", response.getError().toString());
                        final ContentResolver contentResolver = getContext().getContentResolver();
                        final ContentValues contentValues = new ContentValues();
                        try {
                            contentValues.put(iTbUsuColumns.fnStatUsu, true);
                            contentValues.put(iTbUsuColumns.fiIdUsu, object.getInt("id"));
                            contentValues.put(iTbUsuColumns.fcCorreoElectronico, object.getString("email"));
                            contentValues.put(iTbUsuColumns.fcUsu, object.getString("name"));

                            final Uri uri = contentResolver.insert(cTbUsu.CONTENT_URI, contentValues);
                            Intent i = new Intent(getContext(), genericDilog.class);
                            String id = credifastContract.cTbUsu.getUsuId(uri).toString();
                            i.putExtra("message", "Se inserto correctamente " + id);
                            //startActivity(i);

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
}
