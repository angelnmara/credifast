package space.credifast.credifast.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qualtop on 12/03/2018.
 */

public class cAnime {
    private int imagen;
    private String nombre;
    private int vistas;

    public cAnime(int imagen, String nombre, int vistas){
        this.imagen = imagen;
        this.nombre = nombre;
        this.vistas = vistas;
    }

    public int getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVistas() {
        return vistas;
    }

    public static final List<cAnime> ITEMS = new ArrayList<cAnime>();

}
