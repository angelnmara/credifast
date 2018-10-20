package space.credifast.credifast.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import space.credifast.credifast.R;
import space.credifast.credifast.RecyclerViewAdapter.MyTablaAmortizaRecyclerViewAdapter;
import space.credifast.credifast.clases.cAmortiza;
import space.credifast.credifast.fragments.dummy.DummyContent;
import space.credifast.credifast.fragments.dummy.DummyContent.DummyItem;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.facebook.FacebookSdk.getCacheDir;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TablaAmortizaFragment extends BaseVolleyFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_MONTOTOTAL = "MontoTotal";
    private static final String ARG_TASA = "Tasa";
    private static final String ARG_PLAZO = "Plazo";
    private static final String ARG_TIPOTAZA = "TipoTaza";
    // TODO: Customize parameters
    private int mColumnCount;
    private double mMontoTotal;
    private double mTasa;
    private int mPlazo;
    private int mTipotasa;
    private OnListFragmentInteractionListener mListener;

    RequestQueue mRequestQueue;
    RecyclerView recyclerView;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TablaAmortizaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TablaAmortizaFragment newInstance(int columnCount, double MontoTotal, double Tasa, int Plazo, int TipoTaza) {
        TablaAmortizaFragment fragment = new TablaAmortizaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putDouble(ARG_MONTOTOTAL, MontoTotal);
        args.putDouble(ARG_TASA, Tasa);
        args.putInt(ARG_PLAZO, Plazo);
        args.putInt(ARG_TIPOTAZA, TipoTaza);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mMontoTotal = getArguments().getDouble(ARG_MONTOTOTAL);
            mPlazo = getArguments().getInt(ARG_PLAZO);
            mTasa = getArguments().getDouble(ARG_TASA);
            mTipotasa = getArguments().getInt(ARG_TIPOTAZA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tablaamortiza_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //recyclerView.setAdapter(new MyTablaAmortizaRecyclerViewAdapter(cAmortiza));
            generaTabla();
            /*, mListener*/
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(cAmortiza item);
    }

    public void generaTabla(){
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();

        String url = String.format(getString(R.string.apiJava), getString(R.string.TablaAmoritza));

        // Formulate the request and handle the response.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Gson gson = new Gson();
                            JSONArray jsa = response.getJSONArray("tbAmortiza");
                            Type listType = new TypeToken<List<cAmortiza>>(){}.getType();
                            List<cAmortiza> amortiza = gson.fromJson(jsa.toString(), listType);
                            //Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                            recyclerView.setAdapter(new MyTablaAmortizaRecyclerViewAdapter(amortiza, mListener));

                        }catch (Exception ex){
                            Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("monto", String.valueOf(mMontoTotal));
                params.put("tasa", String.valueOf(mTasa));
                params.put("plazo", String.valueOf(mPlazo));
                params.put("tipoTasa", String.valueOf(mTipotasa));
                return params;
            }
        };

        // Add the request to the RequestQueue.
        mRequestQueue.add(jsonObjectRequest);
    }

}
