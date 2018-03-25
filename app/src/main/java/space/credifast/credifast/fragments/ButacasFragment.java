package space.credifast.credifast.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import space.credifast.credifast.R;
import space.credifast.credifast.RecyclerViewAdapter.MyButacas;
import space.credifast.credifast.clases.cButacas;
import space.credifast.credifast.clases.cTokenSaver;
import space.credifast.credifast.interfaces.iTbBoletos;
import space.credifast.credifast.interfaces.iTbSalasButacas;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ButacasFragment extends BaseVolleyFragment implements View.OnClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_idPelicula = "idPelicula";
    private static final String ARG_idSucursal = "idSucursal";
    private static final String ARG_idSala = "idSala";
    private static final String ARG_idHora = "idHora";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int mIdPelicula;
    private int mIdSurucsal;
    private int mIdSala;
    private int mIdHora;
    /*private OnItemClickListener mListener;*/

    Button btnPagar;

    RecyclerView recyclerView;
    List itemsButacas = new ArrayList();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ButacasFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ButacasFragment newInstance(int columnCount, int idPelicula, int idSucursal, int idSala, int idHora) {
        ButacasFragment fragment = new ButacasFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_idPelicula, idPelicula);
        args.putInt(ARG_idSucursal, idSucursal);
        args.putInt(ARG_idSala, idSala);
        args.putInt(ARG_idHora, idHora);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mIdPelicula = getArguments().getInt(ARG_idPelicula);
            mIdSurucsal = getArguments().getInt(ARG_idSucursal);
            mIdSala = getArguments().getInt(ARG_idSala);
            mIdHora = getArguments().getInt(ARG_idHora);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butacas_list, container, false);

        btnPagar = view.findViewById(R.id.btnComprar);

        btnPagar.setOnClickListener(this);

        RecyclerView rv = view.findViewById(R.id.list);

        // Set the adapter
        if (rv instanceof RecyclerView) {
            Context context = rv.getContext();
            recyclerView = (RecyclerView) rv;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            makerequest();

        }

        return view;
    }

    private void makerequest(){

        String url = getResources().getString(R.string.API) + getResources().getString(R.string.database) + "/sp/" + getResources().getString(R.string.GetButacas) + "/" + mIdPelicula + "," + mIdSurucsal + "," + mIdSala + "," + mIdHora;
        itemsButacas.clear();

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
                                    itemsButacas.add(new cButacas(jsonObject.getInt(iTbSalasButacas.fiIdSalaButaca),
                                            jsonObject.getString(iTbSalasButacas.fcSalaButacaFila),
                                            jsonObject.getInt(iTbSalasButacas.fiSalaButacaColumna),
                                            jsonObject.getInt("Vendido"),
                                            jsonObject.getInt(iTbBoletos.fiIdPeliculaHorario),
                                            jsonObject.getString(iTbBoletos.fdBoletoFechaPelicula)
                                    ));
                                }

                                recyclerView.setAdapter(new MyButacas(itemsButacas));

                                /*, mListener*/

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(getContext(), getResources().getString(R.string.NoSucursales), Toast.LENGTH_LONG).show();
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
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        /*mListener = null;*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnComprar:
                Iterator it = itemsButacas.iterator();
                while (it.hasNext()){
                    Object butacas = it.next();
                    if(((cButacas) butacas).getVendido()==2){
                        Toast.makeText(getContext(), ((cButacas) butacas).getSalaButacaFila() + String.valueOf(((cButacas) butacas).getSalaButacaColumna()), Toast.LENGTH_LONG).show();
                    }
                }
                break;
                default:
                    Toast.makeText(getContext(), "Opcion no encontrada", Toast.LENGTH_LONG).show();
                    break;
        }
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
    /*public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(cButacas item);
    }*/

    /*public interface OnItemClickListener{
        void onClickLista(cButacas item);
    }*/
}
