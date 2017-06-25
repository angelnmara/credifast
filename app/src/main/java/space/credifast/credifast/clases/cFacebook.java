package space.credifast.credifast.clases;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONObject;

import space.credifast.credifast.interfaces.iFacebook;

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

    GraphResponse graphResponse;
    GraphRequest graphRequest;
    JSONObject jsonObject;
    AccessToken accessToken;
    String campos;

    @Override
    public void getMe() {
        graphRequest = GraphRequest.newMeRequest(
                accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        jsonObject = object;
                        graphResponse = response;
                        Log.d("salida", object.toString());
                    }
                }
        );
        Bundle params = new Bundle();
        params.putString("fields", campos);
        graphRequest.setParameters(params);
        graphRequest.executeAsync();
    }
}
