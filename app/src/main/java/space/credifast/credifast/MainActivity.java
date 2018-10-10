package space.credifast.credifast;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import space.credifast.credifast.clases.cFacebook;
import space.credifast.credifast.clases.cHoras;
import space.credifast.credifast.clases.cPeliculas;
import space.credifast.credifast.clases.cSucursales;
import space.credifast.credifast.clases.cUsuarioFB;
import space.credifast.credifast.clases.Utilerias;
import space.credifast.credifast.fragments.ButacasFragment;
import space.credifast.credifast.fragments.HorariosFragment;
import space.credifast.credifast.fragments.PeliculasFragment;
import space.credifast.credifast.fragments.FacebookFragment;
import space.credifast.credifast.fragments.SucursalFragment;

import static space.credifast.credifast.clases.cTokenSaver.getIdPelicula;
import static space.credifast.credifast.clases.cTokenSaver.getIdSucursal;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FacebookFragment.OnFragmentInteractionListener,
        PeliculasFragment.OnListFragmentInteractionListener,
        SucursalFragment.OnListFragmentInteractionListener,
        HorariosFragment.OnListFragmentInteractionListener{

    private FragmentManager fm = getSupportFragmentManager();

    TextView tvEmail;
    TextView tvName;
    Context context = this;

    RequestQueue mRequestQueue;

    Utilerias ut = new Utilerias();

    EditText editTextMonto;
    EditText editTextTasa;
    Spinner spinnerTipoTasa;
    EditText editTextPlazo;
    TextView textViewMontoPago;
    Button buttonMontoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        editTextMonto = findViewById(R.id.txtMonto);
        editTextTasa = findViewById(R.id.txtTasa);
        spinnerTipoTasa = findViewById(R.id.ddlTipoTasa);
        editTextPlazo = findViewById(R.id.txtPlazo);
        textViewMontoPago = findViewById(R.id.lblMontoPago);
        buttonMontoPago = findViewById(R.id.btnCalculaMontoPago);

        editTextMonto.setInputType(InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editTextTasa.setInputType(InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editTextPlazo.setInputType(InputType.TYPE_CLASS_NUMBER);

        fillDDL();

        setSupportActionBar(toolbar);

        buttonMontoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String name = spinnerTipoTasa.getSelectedItem().toString();
                    int id = ut.getListMap().get(spinnerTipoTasa.getSelectedItemPosition());
                    Toast.makeText(context, name + " : " + id, Toast.LENGTH_LONG).show();
                }catch (Exception ex){
                    Toast.makeText(context, "Error generico", Toast.LENGTH_LONG).show();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        tvEmail = headerView.findViewById(R.id.textViewEmail);
        tvName = headerView.findViewById(R.id.textViewName);

        final cFacebook cf = new cFacebook();
        final cUsuarioFB cuFB = new cUsuarioFB();
        if(cf.isLoggedIn()){
            cuFB.getUsuarioFB(getApplicationContext(), cf.getUserId());
            tvEmail.setText(cuFB.getFbEmail());
            tvName.setText(cuFB.getFbName());
        }

    }

    public void fillDDL(){
        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();

        String url = String.format(getString(R.string.apiJava), getString(R.string.lamarrullaAPI));

        // Formulate the request and handle the response.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //textViewMontoPago.setText(response.toString());
                        try {
                            ut.setId("fiidplazo");
                            ut.setValue("fcnomplazo");
                            ut.setJsa(response.getJSONArray("tbPlazo"));
                            ut.FnFillDll();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,ut.getListdll());
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerTipoTasa.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Tabla", "tbPlazo");
                return params;
            }
        };

        // Add the request to the RequestQueue.
        mRequestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cartelera) {
            // Handle the camera action
            PeliculasFragment cf = PeliculasFragment.newInstance(0);
            fm.beginTransaction().replace(R.id.lnlPrincipal, cf, "PeliculasFragment").addToBackStack("PeliculasFragment").commit();
        } else if (id == R.id.nav_gallery) {
            FacebookFragment ff = FacebookFragment.newInstance("", "");
            fm.beginTransaction().replace(R.id.lnlPrincipal, ff, "FacebookFragment").addToBackStack("FacebookFragment").commit();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(cPeliculas item) {
        int idPelicula = item.getIdPelicula();
        SucursalFragment sf = SucursalFragment.newInstance(0,idPelicula);
        fm.beginTransaction().replace(R.id.lnlPrincipal, sf, "SucursalFragment").addToBackStack("SucursalFragment").commit();
    }

    @Override
    public void onListFragmentInteraction(cSucursales item) {
        int idSucursal = item.getIdSucursal();
        int mIdPelicula = getIdPelicula(context);
        HorariosFragment hf = HorariosFragment.newInstance(0, idSucursal, mIdPelicula);
        fm.beginTransaction().replace(R.id.lnlPrincipal, hf, "HorariosFragment").addToBackStack("HorariosFragment").commit();
    }

    @Override
    public void onListFragmentInteraction(cHoras item) {
        int mIdPelicula = getIdPelicula(context);
        int mIdSucursal = getIdSucursal(context);
        ButacasFragment bf = ButacasFragment.newInstance(0, mIdPelicula, mIdSucursal, item.getIdSala(), item.getIdHora());
        fm.beginTransaction().replace(R.id.lnlPrincipal, bf, "ButacasFragment").addToBackStack("ButacasFragment").commit();
    }
}
