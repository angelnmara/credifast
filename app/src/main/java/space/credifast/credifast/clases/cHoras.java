package space.credifast.credifast.clases;

/**
 * Created by angel on 22/03/2018.
 */

public class cHoras {
    private int idSucursal;
    private int idPelicula;
    private int idHora;
    private String fdHora;
    private String salaNom;
    private int idSala;

    public cHoras(int idSucursal, int idPelicula, int idHora, String fdHora, String salaNom, int idSala){
        this.idSucursal = idSucursal;
        this.idPelicula = idPelicula;
        this.idHora = idHora;
        this.fdHora = fdHora;
        this.salaNom = salaNom;
        this.idSala = idSala;
    }

    public int getIdSala() {
        return idSala;
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

    public String getSalaNom() {
        return salaNom;
    }
}
