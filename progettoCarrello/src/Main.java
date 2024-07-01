import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Dispositivo> dispositivo = new ArrayList<Dispositivo>();
        dispositivo.add(new Dispositivo("A","a","a",1200,1500, TipoProdotto.SMARTPHONE,1,1,"A1"));

        Magazzino magazzino = new Magazzino(dispositivo);
        Cliente cliente = new Cliente("Duda","duda@duda",2424211,"1234");
        Magazziniere magazziniere = new Magazziniere("Duda","duda@duda",2424211,"1234",magazzino);



        magazzino.VisualizzaDispositivi(cliente);
    }
}