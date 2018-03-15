package space.credifast.credifast.clases;

/**
 * Created by Qualtop on 15/03/2018.
 */

public class cSucursales {
    private int idSucursal;
    private String nomSucursal;
    private int imgSucursal;

    public cSucursales(int idSucursal, String nomSucursal, int imgSucursal){
        this.idSucursal = idSucursal;
        this.nomSucursal = nomSucursal;
        this.imgSucursal = imgSucursal;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public int getImgSucursal() {
        return imgSucursal;
    }

}
