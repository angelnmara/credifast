package space.credifast.credifast.clases;

/**
 * Created by angel on 15/07/2017.
 */

public class cCampos {

    private String nombreCampo;
    private Boolean esLlaveCampo;
    private String tipoCampo;
    private StringBuilder strBuilderCampos;

    public StringBuilder getStrBuilderCampos() {
        return strBuilderCampos;
    }

    public cCampos(String nombreCampo, Boolean esLlaveCampo, String tipoCampo){
        this.nombreCampo = nombreCampo;
        this.esLlaveCampo = esLlaveCampo;
        this.tipoCampo = tipoCampo;
    }

    public void getStringCampo(){
        strBuilderCampos = new StringBuilder();
        strBuilderCampos.append(nombreCampo);
        strBuilderCampos.append(" ");
        switch (tipoCampo){
            case "int":
                strBuilderCampos.append("INTEGER");
                break;
            case "string":
                strBuilderCampos.append("TEXT COLLATE NOCASE");
                break;
            case "bool":
                strBuilderCampos.append("INTEGER");
                break;
            case "decimal":
                strBuilderCampos.append("REAL");
                break;
            case "blob":
                strBuilderCampos.append("BLOB");
                break;
            case "date":
                strBuilderCampos.append("NUMERIC");
                break;
            default:
                strBuilderCampos.append("");
                break;
        }
        if(esLlaveCampo){
            strBuilderCampos.append(" ");
            strBuilderCampos.append("PRIMARY KEY AUTOINCREMENT");
        }
    }
}
