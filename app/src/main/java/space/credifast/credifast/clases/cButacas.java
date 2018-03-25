package space.credifast.credifast.clases;

import java.util.Date;

/**
 * Created by angel on 24/03/2018.
 */

public class cButacas {
    private int idSalaButaca;
    private String salaButacaFila;
    private int salaButacaColumna;
    private int vendido;
    private int idPeliculaHorario;
    private String boletoFechaPelicula;

    public cButacas(int idSalaButaca, String salaButacaFila, int salaButacaColumna, int vendido, int idPeliculaHorario, String boletoFechaPelicula){
        this.idSalaButaca = idSalaButaca;
        this.salaButacaFila = salaButacaFila;
        this.salaButacaColumna = salaButacaColumna;
        this.vendido = vendido;
        this.idPeliculaHorario = idPeliculaHorario;
        this.boletoFechaPelicula = boletoFechaPelicula;
    }

    public int getIdPeliculaHorario() {
        return idPeliculaHorario;
    }

    public String getBoletoFechaPelicula() {
        return boletoFechaPelicula;
    }

    public int getIdSalaButaca() {
        return idSalaButaca;
    }

    public String getSalaButacaFila() {
        return salaButacaFila;
    }

    public int getSalaButacaColumna() {
        return salaButacaColumna;
    }

    public int getVendido() {
        return vendido;
    }

    public void setVendido(int vendido) {
        this.vendido = vendido;
    }
}
