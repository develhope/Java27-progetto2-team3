package GSON.FormDate;
import Progetto.*;

public class TypeGSONProdotto {
    String type;
    Dispositivo properties;

    public TypeGSONProdotto(String type, Dispositivo properties){
        this.type = type;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public Dispositivo getProperties() {
        return properties;
    }
}
