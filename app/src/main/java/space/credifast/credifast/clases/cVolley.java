package space.credifast.credifast.clases;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Qualtop on 13/03/2018.
 */

public class cVolley {
    private static cVolley mVolley = null;
    private RequestQueue mRequestQueue;

    private cVolley(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static cVolley getInstance(Context context){
        if(mVolley == null){
            mVolley = new cVolley(context);
        }
        return mVolley;
    }

    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }

}
