package GSON.FormDate;

import java.time.LocalDateTime;
import java.util.List;
import Progetto.*;

public class TypeGSONCarrello {
    int idCarrello;
    List<TypeGSONProdotto> listProdotto;
    LocalDateTime dateChiusura;

    public TypeGSONCarrello(int idCarrello, List<TypeGSONProdotto> listProdotto, LocalDateTime dateChiusura){
        this.idCarrello = idCarrello;
        this.listProdotto = listProdotto;
        this.dateChiusura = dateChiusura;
    }

    public List<TypeGSONProdotto> getListProdotto() {
        return listProdotto;
    }

    public int getIdCarrello() {
        return idCarrello;
    }
    public LocalDateTime getDateChiusura() {
        return dateChiusura;
    }
}
