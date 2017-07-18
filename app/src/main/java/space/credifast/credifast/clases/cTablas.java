package space.credifast.credifast.clases;

import java.util.Iterator;
import java.util.List;

/**
 * Created by angel on 15/07/2017.
 */

public class cTablas {

    public StringBuilder getStrBuilderTabla() {
        return strBuilderTabla;
    }

    private String nombreTabla;
    private StringBuilder strBuilderTabla;
    private List<cCampos> lcCampos;

    public cTablas(String nombreTabla, List<cCampos> lcCampos){
        this.nombreTabla = nombreTabla;
        this.lcCampos = lcCampos;
    }

    public void queryCreaTabla(){
        strBuilderTabla = new StringBuilder();

        strBuilderTabla.append("CREATE TABLE IF NOT EXISTS ")
                    .append(nombreTabla)
                    .append(" (");
        Iterator<cCampos> itcCampos = lcCampos.iterator();
        while (itcCampos.hasNext()){
            cCampos temp = itcCampos.next();
            temp.getStringCampo();
            strBuilderTabla.append(temp.getStrBuilderCampos());
            if(itcCampos.hasNext()){
                strBuilderTabla.append(", ");
            }
        }
        strBuilderTabla.append(")");
    }

}