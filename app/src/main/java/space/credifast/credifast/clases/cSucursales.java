package space.credifast.credifast.clases;

/**
 * Created by Qualtop on 15/03/2018.
 */

public class cSucursales {
    private int idSucursal;
    private String sucursalDesc;
    private String sucursalDir;
    private double sucursalLat;
    private double sucursalLong;
    private boolean sucursalStat;

    public cSucursales(int idSucursal,
                       String sucursalDesc,
                       String sucursalDir,
                       double sucursalLat,
                       double sucursalLong,
                       boolean sucursalStat){
        this.idSucursal = idSucursal;
        this.sucursalDesc = sucursalDesc;
        this.sucursalDir = sucursalDir;
        this.sucursalLat = sucursalLat;
        this.sucursalLong = sucursalLong;
        this.sucursalStat = sucursalStat;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public String getSucursalDesc() {
        return sucursalDesc;
    }

    public String getSucursalDir() {
        return sucursalDir;
    }

    public double getSucursalLat() {
        return sucursalLat;
    }

    public double getSucursalLong() {
        return sucursalLong;
    }

    public boolean isSucursalStat() {
        return sucursalStat;
    }
}
