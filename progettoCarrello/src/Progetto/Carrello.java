<<<<<<< HEAD:progettoCarrello/src/Progetto/Carrello.java
package Progetto;
import Exceptions.*;
import java.time.LocalDateTime;
=======
import Exception.CarrelloChiusoException;
import java.time.OffsetDateTime;
>>>>>>> 2d0ddd304e861e4298139014b40afd151976f680:progettoCarrello/src/Carrello.java
import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private int idCarrello;
    private List<Prodotto> listaProdottiCarrello;
    private boolean chiuso;
    private LocalDateTime dateChiusura;

    public boolean aggAlCarrelloGSON(Prodotto prodotto){
        return listaProdottiCarrello.add(prodotto);
    }

    public void aggDateChiusura(LocalDateTime dateChiusura) {
        this.dateChiusura = dateChiusura;
        this.chiuso = true;
    }

    public String toStringDetailsClient() {
        return super.toString();
    }
    public LocalDateTime getDateChiusura() {
        return dateChiusura;
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

    public boolean aggiungeIdDispositivoAlCarrello(Prodotto dispositivo) throws CarrelloChiusoException {
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
        dateChiusura = LocalDateTime.now();
        this.chiuso = true;
        return this;
    }

    public void cleanCarrello() {
        listaProdottiCarrello.clear();
    }

    public Double spesaMedia() {
        return listaProdottiCarrello.isEmpty() ? 0.0 : totaleCarrello() / listaProdottiCarrello.size();
    }
}
