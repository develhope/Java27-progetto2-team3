import Exceptions.*;
import GSON.Adapters.*;
import GSON.FormDate.*;
import Progetto.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public void main() throws RicercaNullaException, CarrelloChiusoException {
        List<Utente> utenteList = leggendoUtente();
        Magazzino magazzino = leggendoMagazzino();

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

                    if (!(input2.equals("1") || input2.equals("2"))) {
                        System.out.println("Tipo Utente invalido!");
                    } else {

                        System.out.println("E-mail:");
                        String emailUtente = scanner.nextLine();
                        System.out.println("Password:");
                        String passwordUtente = scanner.nextLine();

                        if (input1.equals("1")) {
                            System.out.println(utenteList);
                            utenteLogin = verificaUtente(utenteList, emailUtente, passwordUtente);
                            if (utenteLogin != null) {
                                login = true;
                            }
                        } else {
                            Utente newUtente = creaUtente(input2, emailUtente, passwordUtente);
                            if (newUtente != null) {
                                utenteList.add(newUtente);
                            }
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
                if (utenteLogin.getTipoUtente().equals(TipoUtente.CLIENTE)) {
                    sceltaMenu = menuCliente(scanner);
                    switch (sceltaMenu) {
                        case "0":
                            break;
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
                    switch (sceltaMenu) {
                        case "1":
                            visualizzaMagazzino(magazzino);
                            break;
                        case "2":

                            break;
                        default:
                            System.out.println("Selezione invalida!");
                    }
                }
                if (sceltaMenu.equals("0")) {
                    esci = true;
                    break;
                }
            }
            scanner.close();
        }
        chiudeMagazzino(magazzino);
        chiudeUtente(utenteList);
    }

    private static void chiudeUtente(List<Utente> utenteList) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter())
                    .excludeFieldsWithModifiers().create();
            FileWriter write = new FileWriter("DatiJSON/dati_Utenti.json");
            List<TypeGSONUtente> typeGSONSUtente = new ArrayList<TypeGSONUtente>();
            for (Utente i : utenteList) {
                if (i != null) {
                    if (i.getTipo().equals("Cliente")) {
                        List<Carrello> listCarrelloAcquist = ((Cliente) i).getStoricoAcquisti();
                        List<TypeGSONCarrello> typeGSONCarrellos = new ArrayList<TypeGSONCarrello>();
                        for (Carrello f : listCarrelloAcquist) {
                            List<TypeGSONProdotto> listProdottoCarrello = new ArrayList<TypeGSONProdotto>();
                            for (Prodotto g : f.getListaProdottiCarrello()) {
                                listProdottoCarrello.add(new TypeGSONProdotto("Dispositivo", (Dispositivo) g));
                            }
                            typeGSONCarrellos.add(new TypeGSONCarrello(f.getIdCarrello(), listProdottoCarrello, f.getDateChiusura()));
                        }
                        TypeGSONCliente cliente = new TypeGSONCliente(((Cliente) i), typeGSONCarrellos);
                        typeGSONSUtente.add(new TypeGSONUtente("Cliente", cliente));
                    } else {
                        typeGSONSUtente.add(new TypeGSONUtente("Magazziniere", (Magazziniere) i));
                    }
                }
            }
            gson.toJson(typeGSONSUtente, write);
            write.close();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void chiudeMagazzino(Magazzino magazzino) {
        try {
            Gson gson = new GsonBuilder().excludeFieldsWithModifiers().create();
            FileWriter write = new FileWriter("DatiJSON/dati_Magazzino.json");
            List<TypeGSONProdotto> typeGSONSDispositivo = new ArrayList<TypeGSONProdotto>();
            List<Prodotto> listProdottoMagazzino = magazzino.getProdotto();
            for (Prodotto i : listProdottoMagazzino) {
                if (i != null) {
                    typeGSONSDispositivo.add(new TypeGSONProdotto("Dispositivo", (Dispositivo) i));
                }
            }
            gson.toJson(typeGSONSDispositivo, write);
            write.close();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    //GESTIONE CARRELLO
    private static Carrello visualizzaCarrello(Carrello carrello, Scanner scanner, Magazzino magazzino, Utente utente) throws RicercaNullaException, CarrelloChiusoException {
        List<Prodotto> carrelloList = carrello.getListaProdottiCarrello();

        System.out.println("=============================================================================================");
        System.out.println("===================================== Carrello atuale: ======================================");
        carrelloList.forEach(i -> System.out.println(i.stampaProdottoCliente()));
        System.out.println("=============================================================================================");
        System.out.println("Total Carrello:" + carrello.totaleCarrello());
        System.out.println("Valore media per prodotti:" + carrello.spesaMedia());
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
            String input = scanner.nextLine();
            UUID idDispositivo = UUID.fromString(input);
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
            if (((Cliente) utente).getStoricoAcquisti().isEmpty()) {
                return new Carrello();
            }
            return new Carrello(((Cliente) utente).getStoricoAcquisti().getLast().getIdCarrello() + 1);
        }
        return carrello;
    }

    private static void stampaStoricoCarrello(Utente utente) {
        List<Carrello> carrelloList = ((Cliente) utente).getStoricoAcquisti();
        for (Carrello i : carrelloList) {
            System.out.println("Carrello: " + i.getIdCarrello() + " | Prezzo totale del carrello: " + i.totaleCarrello());
            System.out.println("Detaglio del'acquisto: " + i.getDateChiusura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            List<Prodotto> carrelloListProdotto = i.getListaProdottiCarrello();
            carrelloListProdotto.forEach(j -> System.out.println(((Dispositivo) j).stampaProdottoCliente()));
            System.out.println();
            System.out.println("=============================================================================================");
        }
    }


    //REGISTRO USER
    public static Utente creaUtente(String input2, String emailUtente, String passwordUtente) {
        Scanner scanner = new Scanner(System.in);
        if (input2.equals("1") || input2.equals("2")) {
            System.out.println("Nome:");
            String nome = scanner.nextLine();
            System.out.println("Telefono: (solo numeri)");
            int telefono = scanner.nextInt();
            if (input2.equals("1")) {
                return new Cliente(nome, emailUtente, telefono, passwordUtente);
            } else {
                return new Magazziniere(nome, emailUtente, telefono, passwordUtente);
            }
        }
        return null;
    }

    public static Utente verificaUtente(List<Utente> utenteList, String emailUtente, String passwordUtente) {
        if ((emailUtente == null) || (passwordUtente == null)) {
            return null;
        }
        for (Utente i : utenteList) {
            if (i != null) {
                if (i.getEmail().equals(emailUtente)) {
                    if (i.getPassword().equals(passwordUtente)) {
                        System.out.println("Login fatto");
                        return i;
                    } else {
                        System.out.println("Password errata!");
                    }
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

    public static List<Utente> leggendoUtente() {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter()).registerTypeAdapter(Utente.class, new ProdottoAbstractElement()).registerTypeAdapter(Prodotto.class, new ProdottoAbstractElement());
            Gson gson = gsonBuilder.create();

            FileReader readerUtenti = new FileReader("DatiJSON/dati_Utenti.json");

            Type userListType = new TypeToken<ArrayList<TypeGSONUtente>>() {
            }.getType();
            List<TypeGSONUtente> typeGONS = gson.fromJson(readerUtenti, userListType);


            List<Utente> utenteList = new ArrayList<>();
            for (TypeGSONUtente i : typeGONS) {
                if (i != null) {
                    if (i.getType().equals("Cliente")) {
                        utenteList.add(new Cliente(i.getCliente().getNome(), i.getCliente().getEmail(), i.getCliente().getTelefono(), i.getCliente().getPassword()));

                        List<TypeGSONCarrello> listCarrello = new ArrayList<TypeGSONCarrello>();
                        if (i.getCliente().getListCarrello() != null) {
                            listCarrello.addAll(i.getCliente().getListCarrello());
                            for (TypeGSONCarrello j : listCarrello) {
                                Carrello carrello = new Carrello();
                                for (TypeGSONProdotto k : j.getListProdotto()) {
                                    carrello.aggAlCarrelloGSON(k.getProperties());
                                }
                                carrello.aggDateChiusura(j.getDateChiusura());
                                ((Cliente) utenteList.getLast()).carrelloFinalizzatto(carrello);
                            }
                        }

                    } else {
                        utenteList.add(new Magazziniere(i.getMagazziniere().getNome(), i.getMagazziniere().getEmail(), i.getMagazziniere().getTelefono(), i.getMagazziniere().getPassword()));
                    }
                }
            }
            return utenteList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Magazzino leggendoMagazzino() {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Prodotto.class, new ProdottoAbstractElement());
            Gson gson = gsonBuilder.create();

            FileReader readerMagazzino = new FileReader("DatiJSON/dati_Magazzino.json");

            Type prodottoListType = new TypeToken<ArrayList<TypeGSONProdotto>>() {
            }.getType();
            List<TypeGSONProdotto> typeGONS = gson.fromJson(readerMagazzino, prodottoListType);

            Magazzino magazzino = new Magazzino();
            for (TypeGSONProdotto i : typeGONS) {
                if (i != null) {
                    if (i.getType().equals("Dispositivo")) {
                        magazzino.aggAlMagazzino(i.getProperties());
                    }
                }
            }
            return magazzino;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Visualizza prodotto magazziniere
    public static String visualizzaMagazzino(Magazzino magazzino) throws RicercaNullaException {
        Scanner scanner = new Scanner(System.in);
        List<Prodotto> prodotto = new ArrayList<>();
        prodotto.addAll(magazzino.visualizzaDispositivi());

        System.out.println("=============================================================================================");
        System.out.println("===================================== Prodotti magazzino: ======================================");
        for (Prodotto i : prodotto) {
            System.out.println(i.toStringDetailsMagazziniere());
        }
        System.out.println("=============================================================================================" + "\n");

        //ricerche varie
        System.out.println("1 - ricerca prodotto | 2 - aggiungi prodotto | 3 - rimuovi prodotto");
        String sceltaSubMenu = scanner.nextLine();

        switch (sceltaSubMenu) {
            case "1":
                System.out.println("1 - ricarca per tipo | 2 - ricerca per produttore | 3 - ricerca per modello");
                System.out.println("4 - ricerca per prezzo vendita | 5 - ricerca per range prezzo | 6 - ricerca per prodotto specifico");
                String sceltaRicerca = scanner.nextLine();
                menuRicerca(sceltaRicerca, magazzino, scanner);
                break;

            case "2":
                System.out.println("Inserisci nome produttore, modello, descrizione, prezzo acquisto, prezzo vendita, dimensione display, dimensione spazio, id del dispositivo, tipo dispositivo");
                aggiungiDispositivo(magazzino, scanner);
                break;
            case "3":
                System.out.println("Inserire id del dispositivo da eliminare");
                magazzino.rimuoveProdotto(UUID.fromString(scanner.nextLine()));
                break;
            default:
                return "Opzione invalida, torna al menu principale!";
        }
        return null;
    }

    public static void menuRicerca(String sceltaRicerca, Magazzino magazzino, Scanner scanner) throws RicercaNullaException {
        switch (sceltaRicerca) {
            case "1":
                String tipo = scanner.nextLine().toUpperCase();
                TipoDispositivo p;
                if (tipo.equals("NOTEBOOK")) {
                    p = TipoDispositivo.NOTEBOOK;
                } else if (tipo.equals("SMARTPHONE")) {
                    p = TipoDispositivo.SMARTPHONE;
                } else if (tipo.equals("TABLET")) {
                    p = TipoDispositivo.TABLET;
                } else {
                    throw new RicercaNullaException();
                }
                magazzino.ricercaTipoDispositivo(p);
                break;

            case "2":
                magazzino.ricercaPerProduttori(scanner.nextLine());
                break;

            case "3":
                magazzino.ricercaPerModelo(scanner.nextLine());
                break;

            case "4":
                magazzino.ricercaPrezzoVendita(scanner.nextDouble());
                break;
            case "5":
                magazzino.ricercaRangePrezzi(scanner.nextDouble(), scanner.nextDouble());
                break;
            case "6":
                magazzino.ricercaProdotto(UUID.fromString(scanner.nextLine()));
                break;
        }
    }

    public static void aggiungiDispositivo(Magazzino magazzino, Scanner scanner) throws RicercaNullaException {


        String produttore = scanner.nextLine();
        String modello = scanner.nextLine();
        String descrizione = scanner.nextLine();
        double prezzoAcquisto = scanner.nextDouble();
        double prezzoVendita = scanner.nextDouble();
        double dimensioneDisplay = scanner.nextDouble();
        int dimensioneSpazio = scanner.nextInt();
        scanner.nextLine();
        String idDispositivo = scanner.nextLine();
        String tipo = scanner.nextLine().toUpperCase();
        TipoDispositivo tipoDispositivo;
        if (tipo.equals("NOTEBOOK")) {
            tipoDispositivo = TipoDispositivo.NOTEBOOK;
        } else if (tipo.equals("SMARTPHONE")) {
            tipoDispositivo = TipoDispositivo.SMARTPHONE;
        } else if (tipo.equals("TABLET")) {
            tipoDispositivo = TipoDispositivo.TABLET;
        } else {
            throw new RicercaNullaException();
        }

        System.out.println(magazzino.aggAlMagazzino(new Dispositivo(produttore, modello, descrizione, prezzoAcquisto, prezzoVendita, tipoDispositivo, dimensioneDisplay, dimensioneSpazio)));
    }
}