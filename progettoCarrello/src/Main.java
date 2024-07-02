import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Dispositivo> dispositivo = new ArrayList<Dispositivo>();
        dispositivo.add(new Dispositivo("A","a","a",1200,1500, TipoProdotto.SMARTPHONE,1,1,"A1"));
        dispositivo.add(new Dispositivo("B","b","b",1100,1400, TipoProdotto.TABLET,1,1,"A5"));
        dispositivo.add(new Dispositivo("C","c","c",1300,1200, TipoProdotto.NOTEBOOK,1,1,"A2"));
        dispositivo.add(new Dispositivo("D","d","d",1500,1000, TipoProdotto.NOTEBOOK,1,1,"A8"));

        Magazzino magazzino = new Magazzino(dispositivo);
        Cliente cliente = new Cliente("Duda","duda@duda",2424211,"1234");
        Magazziniere magazziniere = new Magazziniere("Duda","duda@duda",2424211,"1234",magazzino);



        magazzino.VisualizzaDispositivi(cliente);
        magazzino.VisualizzaDispositivi(magazziniere);

        magazziniere.RimoviIDMagazzino("A1");
        magazzino.VisualizzaDispositivi(cliente);

        System.out.println("Prodotti di tipo Notebook: ");
        magazzino.RicercaTipoDispositivo(TipoProdotto.NOTEBOOK);

        System.out.println("Prodotti di prodottori B: ");
        magazzino.RicercaPerProduttori("B");


    }
}