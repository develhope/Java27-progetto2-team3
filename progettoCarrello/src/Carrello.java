import java.util.ArrayList;
import java.util.List;

public class Carrello {
    int idCarrello;
    List<Prodotto> listaProdottiCarrello;
    boolean chiuso;

    public Carrello() {

    }

    public Carrello(int idCarrello) {
        this.idCarrello = idCarrello;
        listaProdottiCarrello = new ArrayList<Prodotto>();
        chiuso = false;
    }

    public Prodotto aggiungeIdDispositivoAlCarrello(Prodotto dispositivo) throws RicercaNullaException, CarrelloChiusoException {
        if (chiuso) {
            throw new CarrelloChiusoException();
        }
        if (dispositivo != null) {
            listaProdottiCarrello.add(dispositivo);
            return dispositivo;
        }
        return null;
    }

    public Prodotto rimuoviIdDispositivoAlCarrello(String idDispositivo) throws CarrelloChiusoException {
        if (chiuso) {
            throw new CarrelloChiusoException();
        }

        Prodotto item = null;
        for (Prodotto i : listaProdottiCarrello){
            if(i.getIdDispositivo().equals(idDispositivo)){
                item = i;
            }
        }

        listaProdottiCarrello.remove(item);

        return item;
    }

    public Double totaleCarrello() {
        double sumTotal = 0;
        for (Prodotto i : listaProdottiCarrello){
            sumTotal += i.getPrezzoVendita();
        }
        return sumTotal;
    }

    public int finalizaCompra() {
        this.chiuso = true;
        return idCarrello;
    }
}
