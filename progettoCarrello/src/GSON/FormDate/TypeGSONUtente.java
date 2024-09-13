package GSON.FormDate;
import Progetto.*;
public class TypeGSONUtente {
    String type;
    TypeGSONCliente cliente;
    Magazziniere magazziniere;

    public TypeGSONUtente(String type, TypeGSONCliente cliente) {
        this.type = type;
        this.cliente = cliente;
    }

    public TypeGSONUtente(String type, Magazziniere magazziniere) {
        this.type = type;
        this.magazziniere = magazziniere;
    }

    public String getType() {
        return type;
    }

    public TypeGSONCliente getCliente() {
        return cliente;
    }

    public Magazziniere getMagazziniere() {
        return magazziniere;
    }
}
