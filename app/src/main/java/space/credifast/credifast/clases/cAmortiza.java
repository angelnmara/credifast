package space.credifast.credifast.clases;

public class cAmortiza {
    private double fdpagoamortiza;
    private double fdcapitalpagadoamortiza;
    private int fiidusutabla;
    private double fdinteresespagadosamortiza;
    private  double fdmontoprestamoamortiza;
    private  int finumpagoamortiza;

    public cAmortiza(double fdpagoamortiza,
                    double fdcapitalpagadoamortiza,
                    int fiidusutabla,
                    double fdinteresespagadosamortiza,
                     double fdmontoprestamoamortiza,
                     int finumpagoamortiza){
        this.fdpagoamortiza = fdpagoamortiza;
        this.fdcapitalpagadoamortiza = fdcapitalpagadoamortiza;
        this.fiidusutabla = fiidusutabla;
        this.fdinteresespagadosamortiza = fdinteresespagadosamortiza;
        this.fdmontoprestamoamortiza = fdmontoprestamoamortiza;
        this.finumpagoamortiza = finumpagoamortiza;
    }

    public double getFdpagoamortiza() {
        return fdpagoamortiza;
    }

    public void setFdpagoamortiza(double fdpagoamortiza) {
        this.fdpagoamortiza = fdpagoamortiza;
    }

    public double getFdcapitalpagadoamortiza() {
        return fdcapitalpagadoamortiza;
    }

    public void setFdcapitalpagadoamortiza(double fdcapitalpagadoamortiza) {
        this.fdcapitalpagadoamortiza = fdcapitalpagadoamortiza;
    }

    public int getFiidusutabla() {
        return fiidusutabla;
    }

    public void setFiidusutabla(int fiidusutabla) {
        this.fiidusutabla = fiidusutabla;
    }

    public double getFdinteresespagadosamortiza() {
        return fdinteresespagadosamortiza;
    }

    public void setFdinteresespagadosamortiza(double fdinteresespagadosamortiza) {
        this.fdinteresespagadosamortiza = fdinteresespagadosamortiza;
    }

    public double getFdmontoprestamoamortiza() {
        return fdmontoprestamoamortiza;
    }

    public void setFdmontoprestamoamortiza(double fdmontoprestamoamortiza) {
        this.fdmontoprestamoamortiza = fdmontoprestamoamortiza;
    }

    public int getFinumpagoamortiza() {
        return finumpagoamortiza;
    }

    public void setFinumpagoamortiza(int finumpagoamortiza) {
        this.finumpagoamortiza = finumpagoamortiza;
    }
}
