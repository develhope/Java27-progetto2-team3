import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private int idCarrello;
    private List<Prodotto> listaProdottiCarrello;
    private boolean chiuso;

    public String toStringDetailsClient() {
        return super.toString();
    }

    public List<Prodotto> getListaProdottiCarrello() {
        return listaProdottiCarrello;
    }

    public Carrello() {
        this.idCarrello = idCarrello;
        listaProdottiCarrello = new ArrayList<Prodotto>();
        chiuso = false;
    }

    public Carrello(int idCarrello) {
        this.idCarrello = idCarrello;
        listaProdottiCarrello = new ArrayList<Prodotto>();
        chiuso = false;
    }

    public int getIdCarrello() {
        return idCarrello;
    }

    public boolean isChiuso() {
        return chiuso;
    }

    public boolean aggiungeIdDispositivoAlCarrello(Prodotto dispositivo) throws RicercaNullaException, CarrelloChiusoException {
        if (chiuso) {
            throw new CarrelloChiusoException();
        }
        if (dispositivo != null) {
            return listaProdottiCarrello.add(dispositivo);
        }
        return false;
    }

    public Prodotto rimuoviIdDispositivoAlCarrello(String idDispositivo) throws CarrelloChiusoException {
        if (chiuso) {
            throw new CarrelloChiusoException();
        }

        Prodotto item = null;
        for (Prodotto i : listaProdottiCarrello) {
            if (((Dispositivo) i).getIdDispositivo().equals(idDispositivo)) {
                item = i;
            }
        }

        listaProdottiCarrello.remove(item);

        return item;
    }

    public Double totaleCarrello() {
        double sumTotal = 0;
        for (Prodotto i : listaProdottiCarrello) {
            sumTotal += i.getPrezzoVendita();
        }
        return sumTotal;
    }

    public Carrello finalizaCompra() {
        this.chiuso = true;
        return this;
    }

    public void cleanCarrello() {
        listaProdottiCarrello.clear();
    }
}
