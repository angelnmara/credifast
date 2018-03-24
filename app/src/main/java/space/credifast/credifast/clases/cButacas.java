package space.credifast.credifast.clases;

/**
 * Created by angel on 24/03/2018.
 */

public class cButacas {
    private int idSalaButaca;
    private String salaButacaFila;
    private int salaButacaColumna;
    private int vendido;
    private boolean isSelected = false;

    public cButacas(int idSalaButaca, String salaButacaFila, int salaButacaColumna, int vendido){
        this.idSalaButaca = idSalaButaca;
        this.salaButacaFila = salaButacaFila;
        this.salaButacaColumna = salaButacaColumna;
        this.vendido = vendido;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public int isVendido() {
        return vendido;
    }
}
