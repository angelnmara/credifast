package space.credifast.credifast.clases;

/**
 * Created by angel on 22/03/2018.
 */

public class cHorarios {
    private int idSucursal;
    private int idPelicula;
    private int idHora;
    private String fdHora;

    public cHorarios(int idSucursal, int idPelicula, int idHora, String fdHora){
        this.idSucursal = idSucursal;
        this.idPelicula = idPelicula;
        this.idHora = idHora;
        this.fdHora = fdHora;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public int getIdHora() {
        return idHora;
    }

    public String getFdHora() {
        return fdHora;
    }
}
