package Test;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class MagazzinoTest {

    private Magazzino magazzino;
    private Dispositivo dispositivo1;
    private Dispositivo dispositivo2;
    private Dispositivo dispositivo3;

    @Before
    public void setUp() {
        magazzino = new Magazzino();
        dispositivo1 = new Dispositivo("Apple", "iPhone 13", "Smartphone", 700, 1000, TipoDispositivo.SMARTPHONE, 6.1, 128, "D001");
        dispositivo2 = new Dispositivo("Samsung", "Galaxy Tab", "Tablet", 400, 600, TipoDispositivo.TABLET, 10.5, 256, "D002");
        dispositivo3 = new Dispositivo("Apple", "MacBook Pro", "Notebook", 1500, 2000, TipoDispositivo.SMARTPHONE, 13.3, 512, "D003");
        magazzino.aggAlMagazzino(dispositivo1);
        magazzino.aggAlMagazzino(dispositivo2);
        magazzino.aggAlMagazzino(dispositivo3);
    }

    @Test
    public void testAggAlMagazzino() {
        Dispositivo dispositivo4 = new Dispositivo("Google", "Pixel 5", "Smartphone", 500, 700, TipoDispositivo.SMARTPHONE, 6.0, 128, "D004");
        assertTrue("Il dispositivo dovrebbe essere aggiunto correttamente", magazzino.aggAlMagazzino(dispositivo4));
        assertEquals("La dimensione della lista dovrebbe essere 4", 4, magazzino.getProdotto().size());
    }

    @Test
    public void testRicercaTipoDispositivoNonEsistente() {
        assertThrows(RicercaNullaException.class, () ->
                        magazzino.ricercaTipoDispositivo(TipoDispositivo.NOTEBOOK)
                ,"Dovrebbe lanciare Exception.RicercaNullaException se il tipo di dispositivo non esiste");
    }

    @Test
    public void testRicercaPerProduttori() throws RicercaNullaException {
        List<Prodotto> appleProducts = magazzino.ricercaPerProduttori("Apple");
        assertEquals("Dovrebbero esserci 2 dispositivi Apple", 2, appleProducts.size());
        assertTrue("La lista dovrebbe contenere dispositivo1", appleProducts.contains(dispositivo1));
        assertTrue("La lista dovrebbe contenere dispositivo3", appleProducts.contains(dispositivo3));
    }

    @Test
    public void testRicercaPerProduttoriNonEsistente() {
        assertThrows(RicercaNullaException.class, () -> {
            magazzino.ricercaPerProduttori("Sony");
        }, "Dovrebbe lanciare Exception.RicercaNullaException se il produttore non esiste");
    }

    @Test
    public void testRicercaPerModelo() throws RicercaNullaException {
        List<Prodotto> galaxyTab = magazzino.ricercaPerModelo("Galaxy Tab");
        assertEquals("Dovrebbe esserci un solo Galaxy Tab", 1, galaxyTab.size());
        assertTrue("La lista dovrebbe contenere dispositivo2", galaxyTab.contains(dispositivo2));
    }

    @Test
    public void testRicercaPerModeloNonEsistente() {
        assertThrows(RicercaNullaException.class, () -> {
            magazzino.ricercaPerModelo("Surface Pro");
        }, "Dovrebbe lanciare Exception.RicercaNullaException se il modello non esiste");
    }

    @Test
    public void testRicercaPrezzoVendita() throws RicercaNullaException {
        List<Prodotto> prodotti600 = magazzino.ricercaPrezzoVendita(600.0);
        assertEquals("Dovrebbe esserci un dispositivo con prezzo di vendita 600", 1, prodotti600.size());
        assertTrue("La lista dovrebbe contenere dispositivo2", prodotti600.contains(dispositivo2));
    }

    @Test
    public void testRicercaProdotto() throws RicercaNullaException {
        Prodotto prodotto = magazzino.ricercaProdotto("D001");
        assertEquals("Dovrebbe restituire dispositivo1", dispositivo1, prodotto);
    }

    @Test
    public void testRicercaProdottoNonEsistente() {
        assertThrows(RicercaNullaException.class, () -> {
            magazzino.ricercaProdotto("D999");
        }, "Dovrebbe lanciare Exception.RicercaNullaException se il prodotto non esiste");
    }

    @Test
    public void testRimuoveProdotto() throws RicercaNullaException {
        assertTrue("Il dispositivo dovrebbe essere rimosso correttamente", magazzino.rimuoveProdotto("D001"));
        assertEquals("La dimensione della lista dovrebbe essere 2", 2, magazzino.getProdotto().size());
    }

}