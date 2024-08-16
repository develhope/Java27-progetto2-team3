package Test;

import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CarrelloTest {
    private Carrello carrello;
    private Dispositivo dispositivo1;
    private Dispositivo dispositivo2;

    @Before
    public void setUp() {
        carrello = new Carrello();
        dispositivo1 = new Dispositivo("Apple", "iPhone 13", "Smartphone", 700, 1000, TipoDispositivo.SMARTPHONE, 6.1, 128, "D001");
        dispositivo2 = new Dispositivo("Samsung", "Galaxy Tab", "Tablet", 400, 600, TipoDispositivo.TABLET, 10.5, 256, "D002");
    }

    @Test
    public void testAggiungeIdDispositivoAlCarrello() throws RicercaNullaException, CarrelloChiusoException {
        assertTrue("Il dispositivo dovrebbe essere aggiunto correttamente", carrello.aggiungeIdDispositivoAlCarrello(dispositivo1));
        assertEquals("La dimensione della lista dovrebbe essere 1", 1, carrello.getListaProdottiCarrello().size());
    }

    @Test
    public void testAggiungeIdDispositivoAlCarrelloQuandoChiuso() throws CarrelloChiusoException {
        carrello.finalizaCompra();
        assertThrows(CarrelloChiusoException.class, () -> {
            carrello.aggiungeIdDispositivoAlCarrello(dispositivo1);
        }, "Dovrebbe lanciare Exception.CarrelloChiusoException se il carrello è chiuso");
    }

    @Test
    public void testRimuoviIdDispositivoAlCarrello() throws RicercaNullaException, CarrelloChiusoException {
        carrello.aggiungeIdDispositivoAlCarrello(dispositivo1);
        Prodotto rimosso = carrello.rimuoviIdDispositivoAlCarrello("D001");
        assertEquals("Il dispositivo rimosso dovrebbe essere dispositivo1", dispositivo1, rimosso);
        assertEquals("La dimensione della lista dovrebbe essere 0", 0, carrello.getListaProdottiCarrello().size());
    }

    @Test
    public void testRimuoviIdDispositivoAlCarrelloQuandoChiuso() throws CarrelloChiusoException, RicercaNullaException {
        carrello.aggiungeIdDispositivoAlCarrello(dispositivo1);
        carrello.finalizaCompra();
        assertThrows(CarrelloChiusoException.class, () -> {
            carrello.rimuoviIdDispositivoAlCarrello("D001");
        }, "Dovrebbe lanciare Exception.CarrelloChiusoException se il carrello è chiuso");
    }

    @Test
    public void testTotaleCarrello() throws RicercaNullaException, CarrelloChiusoException {
        carrello.aggiungeIdDispositivoAlCarrello(dispositivo1);
        carrello.aggiungeIdDispositivoAlCarrello(dispositivo2);
        assertEquals("Il totale del carrello dovrebbe essere 1600", 1600.0, carrello.totaleCarrello(), 0.0);
    }

    @Test
    public void testFinalizaCompra() throws RicercaNullaException, CarrelloChiusoException {
        carrello.aggiungeIdDispositivoAlCarrello(dispositivo1);
        carrello.finalizaCompra();
        assertTrue("Il carrello dovrebbe essere chiuso", carrello.isChiuso());
        assertNotNull("La data di chiusura dovrebbe essere settata", carrello.getDateChiusura());
    }

    @Test
    public void testCleanCarrello() throws RicercaNullaException, CarrelloChiusoException {
        carrello.aggiungeIdDispositivoAlCarrello(dispositivo1);
        carrello.cleanCarrello();
        assertEquals("La dimensione della lista dovrebbe essere 0", 0, carrello.getListaProdottiCarrello().size());
    }

    @Test
    public void testSpesaMedia() throws RicercaNullaException, CarrelloChiusoException {
        carrello.aggiungeIdDispositivoAlCarrello(dispositivo1);
        carrello.aggiungeIdDispositivoAlCarrello(dispositivo2);
        assertEquals("La spesa media dovrebbe essere 800", 800.0, carrello.spesaMedia(), 0.0);
    }

    @Test
    public void testSpesaMediaConCarrelloVuoto() {
        assertEquals("La spesa media dovrebbe essere 0 con carrello vuoto", 0.0, carrello.spesaMedia(), 0.0);
    }
}


