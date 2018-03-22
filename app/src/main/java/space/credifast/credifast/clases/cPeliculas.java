package space.credifast.credifast.clases;

/**
 * Created by Qualtop on 12/03/2018.
 */

public class cPeliculas {
    private int idPelicula;
    private String peliculaDesc;
    private int idGenero;
    private String peliculaDuracion;
    private int idClasificacion;
    private String peliculaSinopsis;
    private String peliculaActores;
    private String peliculaDirectores;
    private String peliculaURL;
    private boolean peliculaStat;


    public cPeliculas(int idPelicula,
                      String peliculaDesc,
                      int idGenero,
                      String peliculaDuracion,
                      int idClasificacion,
                      String peliculaSinopsis,
                      String peliculaActores,
                      String peliculaDirectores,
                      String peliculaURL,
                      boolean peliculaStat){
        this.idPelicula = idPelicula;
        this.peliculaDesc = peliculaDesc;
        this.idGenero = idGenero;
        this.peliculaDuracion = peliculaDuracion;
        this.peliculaSinopsis = peliculaSinopsis;
        this.peliculaActores = peliculaActores;
        this.peliculaDirectores = peliculaDirectores;
        this.peliculaURL = peliculaURL;
        this.peliculaStat = peliculaStat;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public String getPeliculaDesc() {
        return peliculaDesc;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public String getPeliculaDuracion() {
        return peliculaDuracion;
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public String getPeliculaSinopsis() {
        return peliculaSinopsis;
    }

    public String getPeliculaActores() {
        return peliculaActores;
    }

    public String getPeliculaDirectores() {
        return peliculaDirectores;
    }

    public String getPeliculaURL() {
        return peliculaURL;
    }

    public boolean isPeliculaStat() {
        return peliculaStat;
    }

}
