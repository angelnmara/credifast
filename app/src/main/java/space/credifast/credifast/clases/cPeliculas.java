package space.credifast.credifast.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qualtop on 12/03/2018.
 */

public class cPeliculas {
    private int idPelicula;
    private String nomPelicula;
    private int imgPelicula;

    public cPeliculas(int idPelicula, String nomPelicula, int imgPelicula){
        this.idPelicula = idPelicula;
        this.nomPelicula = nomPelicula;
        this.imgPelicula = imgPelicula;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public String getNomPelicula() {
        return nomPelicula;
    }

    public int getImgPelicula() {
        return imgPelicula;
    }

}
