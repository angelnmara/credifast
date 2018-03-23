package space.credifast.credifast;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import space.credifast.credifast.clases.cFacebook;
import space.credifast.credifast.clases.cHoras;
import space.credifast.credifast.clases.cPeliculas;
import space.credifast.credifast.clases.cSucursales;
import space.credifast.credifast.clases.cUsuarioFB;
import space.credifast.credifast.fragments.HorariosFragment;
import space.credifast.credifast.fragments.PeliculasFragment;
import space.credifast.credifast.fragments.FacebookFragment;
import space.credifast.credifast.fragments.SucursalFragment;

import static space.credifast.credifast.clases.cTokenSaver.getIdPelicula;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        int idPelicula = getIdPelicula(context);
        HorariosFragment hf = HorariosFragment.newInstance(0, idSucursal, idPelicula);
        fm.beginTransaction().replace(R.id.lnlPrincipal, hf, "HorariosFragment").addToBackStack("HorariosFragment").commit();
    }

    @Override
    public void onListFragmentInteraction(cHoras item) {
        Toast.makeText(context, "hora", Toast.LENGTH_LONG).show();
    }
}
