package space.credifast.credifast.clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Utilerias {

    private HashMap<Integer,Integer> listMap = new HashMap<Integer, Integer>();
    private ArrayList<String> listdll = new ArrayList<String>();
    private JSONArray jsa = new JSONArray();
    private String id;
    private String value;

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HashMap<Integer, Integer> getListMap() {
        return listMap;
    }

    public ArrayList<String> getListdll() {
        return listdll;
    }

    public void setJsa(JSONArray jsa) {
        this.jsa = jsa;
    }

    public void FnFillDll() throws JSONException {
        for(int i=0; i<jsa.length(); i++){
            JSONObject jso = jsa.getJSONObject(i);
            listMap.put(i, jso.getInt(id));
            listdll.add(jso.getString(value));
        }
    }
}
