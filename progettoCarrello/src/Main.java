
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RicercaNullaException, CarrelloChiusoException {

        List<Prodotto> prodottoList = new ArrayList<Prodotto>();
        prodottoList.add(new Dispositivo("a", "a", "a", 130, 180, TipoDispositivo.NOTEBOOK, 12, 12, "a"));
        prodottoList.add(new Dispositivo("b", "b", "b", 130, 180, TipoDispositivo.NOTEBOOK, 12, 12, "b"));
        prodottoList.add(new Dispositivo("c", "c", "c", 130, 180, TipoDispositivo.NOTEBOOK, 12, 12, "c"));
        prodottoList.add(new Dispositivo("d", "d", "d", 130, 180, TipoDispositivo.NOTEBOOK, 12, 12, "d"));

        Magazzino magazzino = new Magazzino((ArrayList<Prodotto>) prodottoList);

        List<Utente> utenteList = new ArrayList<>();
        utenteList.add(new Cliente("Duda", "a", 1, "a"));

        boolean esci = false;
        System.out.println("|--------------------------------- BENVENUTI AL MERCATO TECH ----------------------------------|");
        while (!esci) {

            boolean login = false;
            Utente utenteLogin = null;
            Scanner scanner = new Scanner(System.in);

            while (!login && !esci) {
                System.out.println("|-----------------------------  1- LogIn | 2- SignOut | 3- Exit  ------------------------------|");
                String input1 = scanner.nextLine();

                if (input1.equals("1") || input1.equals("2")) {

                    System.out.println("|------------------------------   1- Cliente | 2- Magazziniere  -------------------------------|");
                    String input2 = scanner.nextLine();

                    if (!(input1.equals("1") || input1.equals("2"))) {
                        System.out.println("Tipo Utente invalido!");
                    } else {

                        System.out.println("E-mail:");
                        String emailUtente = scanner.nextLine();
                        System.out.println("Password:");
                        String passwordUtente = scanner.nextLine();

                        if (input1.equals("1")) {
                            utenteLogin = verificaUtente(utenteList, emailUtente, passwordUtente);
                            if (utenteLogin != null) {
                                login = true;
                            }
                        } else if (input1.equals("2")) {
                            utenteList.add(creaUtente(input2, emailUtente, passwordUtente));
                        }
                    }
                } else if (input1.equals("3")) {
                    esci = true;
                } else {
                    System.out.println("input invalido!");
                }
            }

            if (login) {
                System.out.println("|----------------------------- Benvenuto " + utenteLogin.getNome() + "! ---------------------------|");
            }

            Carrello carrello = new Carrello();
            while (login && !esci) {
                String sceltaMenu;
                if (utenteLogin.getTipo().equals("Cliente")) {
                    sceltaMenu = menuCliente(scanner);
                    switch (sceltaMenu) {
                        case "1":
                            carrello = visualizzaCarrello(carrello, scanner, magazzino, utenteLogin);
                            break;
                        case "2":
                            stampaStoricoCarrello(utenteLogin);
                            break;
                        default:
                            System.out.println("Selezione invalida!");
                    }
                } else {
                    sceltaMenu = menuMagazzinieri(scanner);
                }
                if (sceltaMenu == "0") {
                    esci = true;
                    break;
                }
            }
            scanner.close();
        }
    }

    //GESTIONE CARRELLO
    private static Carrello visualizzaCarrello(Carrello carrello, Scanner scanner, Magazzino magazzino, Utente utente) throws RicercaNullaException, CarrelloChiusoException {
        List<Prodotto> carrelloList = carrello.getListaProdottiCarrello();
        System.out.println("=============================================================================================");
        System.out.println("===================================== Carrello atuale: ======================================");
        carrelloList.forEach(i -> System.out.println(((Dispositivo) i).stampaProdottoCliente()));
        System.out.println("=============================================================================================");
        System.out.println("Total Carrello:" + carrello.totaleCarrello());
        System.out.println("");

        System.out.println("1 - Agg Carrello | 2 - Rimuove Carrello | 3 - Finaliza Carrello");
        String sceltaSubMenu = scanner.nextLine();

        switch (sceltaSubMenu) {
            case "1":
                return aggCarrello(scanner, magazzino, carrello);
            case "2":
                return rimCarrello(scanner, carrello, magazzino);
            case "3":
                return chiudeCarrello(utente, carrello);
            default:
                System.out.println("Opzione invalida, torna al menu principale!");
                return carrello;
        }
    }

    private static Carrello aggCarrello(Scanner scanner, Magazzino magazzino, Carrello carrello) {
        try {
            System.out.println("ID dispositivo:");
            String idDispositivo = scanner.nextLine();
            Prodotto prodotto = magazzino.ricercaProdotto(idDispositivo);
            if (carrello.aggiungeIdDispositivoAlCarrello(prodotto)) {
                magazzino.rimuoveProdotto(idDispositivo);
                System.out.println("Prodotto di modello: " + prodotto.getModello() + " aggiunto al carrello!");
                return carrello;
            }
        } catch (RicercaNullaException | CarrelloChiusoException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Prodotto non aggiunto!");
        return carrello;
    }

    private static Carrello rimCarrello(Scanner scanner, Carrello carrello, Magazzino magazzino) {
        try {
            System.out.println("ID dispositivo:");
            String idDispositivo = scanner.nextLine();
            Prodotto prodotto = carrello.rimuoviIdDispositivoAlCarrello(idDispositivo);
            if (magazzino.aggAlMagazzino(prodotto)) {
                System.out.println("Prodotto di modello: " + prodotto.getModello() + " rimosso dal carrello!");
                return carrello;
            }
        } catch (NullPointerException | CarrelloChiusoException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Prodotto non rimosso!");
        return carrello;
    }

    private static Carrello chiudeCarrello(Utente utente, Carrello carrello) {
        if (((Cliente) utente).carrelloFinalizzatto(carrello.finalizaCompra())) {
            System.out.println("Carrello chiuso!");
            if(((Cliente) utente).getStoricoAcquisti().isEmpty()){
                return new Carrello();
            }
            return new Carrello(((Cliente) utente).getStoricoAcquisti().getLast().getIdCarrello() + 1);
        }
        return carrello;
    }

    private static void stampaStoricoCarrello(Utente utente) {
        ArrayList<Carrello> carrelloList = ((Cliente) utente).getStoricoAcquisti();
        for (Carrello i : carrelloList) {
            System.out.println("Carrello: " + i.getIdCarrello() + " | Prezzo totale del carrello: " + i.totaleCarrello());
            List<Prodotto> carrelloListProdotto = i.getListaProdottiCarrello();
            carrelloListProdotto.forEach(j -> System.out.println(((Dispositivo) j).stampaProdottoCliente()));
            System.out.println();
            System.out.println("=============================================================================================");
        }
    }


    //REGISTRO USER
    public static Utente creaUtente(String input2, String emailUtente, String passwordUtente) {
        Scanner scanner = new Scanner(System.in);
        Utente utente;
        System.out.println("Nome:");
        String nome = scanner.nextLine();
        System.out.println("Telefono: (solo numeri)");
        int telefono = scanner.nextInt();
        if (input2.equals("1")) {
            return new Cliente(nome, emailUtente, telefono, passwordUtente);
        } else if (input2.equals("2")) {
            return new Magazziniere(nome, emailUtente, telefono, passwordUtente);
        }
        return null;
    }

    public static Utente verificaUtente(List<Utente> utenteList, String emailUtente, String passwordUtente) {
        if ((emailUtente == null) || (passwordUtente == null)) {
            return null;
        }
        for (Utente i : utenteList) {
            if (i.getEmail().equals(emailUtente)) {
                if (i.getPassword().equals(passwordUtente)) {
                    System.out.println("Login fatto");
                    return i;
                } else {
                    System.out.println("Password errata!");
                }
            }
        }
        System.out.println("Utente inesistente! Crea un nuovo account!");
        return null;
    }


    //STAMPA MENU
    public static String menuCliente(Scanner scanner) {
        System.out.println("|---------- 1- Carrello Atuale | 2- Visualizza storico carrello | 3- Lista Prodotti -----------|");
        //Nel carrello atuale deve avere agg e rim prodotto carrello
        //Nella lista Prodotti deve avere tutte le ricerche
        System.out.println("|---------------------------- 4- Modifica dati personale | 0- Esci ----------------------------|");
        return scanner.nextLine();
    }

    public static String menuMagazzinieri(Scanner scanner) {
        System.out.println("|-------------------- 1- Visualizza magazzino | 2- Aggiunge nuovo prodotto --------------------|");
        //Nel Visualizza magazzino deve avere tutte le ricerche
        System.out.println("|---------------------------- 3- Modifica dati personale | 0- Esci ----------------------------|");
        return scanner.nextLine();
    }
}
