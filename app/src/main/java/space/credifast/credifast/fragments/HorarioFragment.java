package space.credifast.credifast.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import space.credifast.credifast.R;
import space.credifast.credifast.RecyclerViewAdapter.MyHorario;
import space.credifast.credifast.clases.cHorarios;
import space.credifast.credifast.clases.cTokenSaver;
import space.credifast.credifast.interfaces.iTablas;
import space.credifast.credifast.interfaces.iTbHoras;
import space.credifast.credifast.interfaces.iTbPeliculas;
import space.credifast.credifast.interfaces.iTbSucursales;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HorarioFragment extends BaseVolleyFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_idPelicula = "idPelicula";
    private static final String ARG_idSucursal = "idSucursal";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    List itemsHorarios = new ArrayList();
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HorarioFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HorarioFragment newInstance(int columnCount, int idPelicula, int idSucursal) {
        HorarioFragment fragment = new HorarioFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_idPelicula, idPelicula);
        args.putInt(ARG_idSucursal, idSucursal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horario_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //recyclerView.setAdapter(new MyHorario(DummyContent.ITEMS, mListener));

            makerequest();
        }
        return view;
    }

    private void makerequest(){

        String url = getResources().getString(R.string.API) + getResources().getString(R.string.database) + "/sp/" + getResources().getString(R.string.GetHorariosXPeliculaXSucursal) + "/" + getArguments().getInt(ARG_idPelicula) + "," + getArguments().getInt(ARG_idSucursal);
        itemsHorarios.clear();

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response);
                            JSONArray jsonArray = response.getJSONArray(getResources().getString(R.string.GetHorariosXPeliculaXSucursal));
                            if(jsonArray!=null){
                                int len = jsonArray.length();
                                for(int i=0; i<len;i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    itemsHorarios.add(new cHorarios(jsonObject.getInt(iTbSucursales.fiIdSucursal),
                                            jsonObject.getInt(iTbPeliculas.fiIdPelicula),
                                            jsonObject.getInt(iTbHoras.fiIdHora),
                                            jsonObject.getString(iTbHoras.fdHora)));
                                }
                                recyclerView.setAdapter(new MyHorario(itemsHorarios, mListener));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<>();
            headers.put(getResources().getString(R.string.token), cTokenSaver.getToken(getContext()));
            return headers;
        }
        };

        addToQueue(request);
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
        void onListFragmentInteraction(cHorarios item);
    }
}