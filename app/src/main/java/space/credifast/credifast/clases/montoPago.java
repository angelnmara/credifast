package space.credifast.credifast.clases;

public class montoPago {
    public double MontoTotal;
    public double Tasa;
    public int Plazo;
    public int TipoTaza;
    public double MontoPago;

    public double getMontoTotal() {
        return MontoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        MontoTotal = montoTotal;
    }

    public double getTasa() {
        return Tasa;
    }

    public void setTasa(double tasa) {
        Tasa = tasa;
    }

    public int getPlazo() {
        return Plazo;
    }

    public void setPlazo(int plazo) {
        Plazo = plazo;
    }

    public int getTipoTaza() {
        return TipoTaza;
    }

    public void setTipoTaza(int tipoTaza) {
        TipoTaza = tipoTaza;
    }

    public double getMontoPago() {
        return MontoPago;
    }

    public void setMontoPago(double montoPago) {
        MontoPago = montoPago;
    }

    public void calculaMontoPago(){
        Tasa = Tasa/100;
        double TasaMensual = RegresaTasa();
        double PlazoD = Plazo;
        MontoPago = (TasaMensual / (1 - Math.pow((1+TasaMensual), (-1*PlazoD)))) * MontoTotal;
    }

    public Double RegresaTasa(){
        Double RegresaTasa = 0.0;
        switch(TipoTaza){
            case 1:
                RegresaTasa = (Tasa * ((360/7)/12));
                break;
            case 2:
                RegresaTasa = (Tasa * (((360/7)/12)/2));
                break;
            case 3:
                RegresaTasa = Tasa;
                break;
            case 4:
                RegresaTasa = (Tasa/12);
                break;
                default:
                    RegresaTasa = Tasa;
                    break;
        }
        return RegresaTasa;
    }

}
