import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                int input1 = scanner.nextInt();

                if (input1 == 1 || input1 == 2) {

                    System.out.println("|------------------------------   1- Cliente | 2- Magazziniere  -------------------------------|");
                    int input2 = scanner.nextInt();

                    if (!((input2 == 1) || (input2 == 2))) {
                        System.out.println("Tipo Utente invalido!");
                    } else {

                        scanner.nextLine();
                        System.out.println("E-mail:");
                        String emailUtente = scanner.nextLine();
                        System.out.println("Password:");
                        String passwordUtente = scanner.nextLine();

                        if (input1 == 1) {
                            utenteLogin = verificaUtente(utenteList, emailUtente, passwordUtente);
                            if (utenteLogin != null) {
                                login = true;
                            }
                        } else if (input1 == 2) {
                            utenteList.add(creaUtente(input1, input2, emailUtente, passwordUtente));
                        }
                    }
                } else if (input1 == 3) {
                    esci = true;
                } else {
                    System.out.println("input invalido!");
                }
            }

            if (login) {
                System.out.println("|----------------------------- Benvenuto " + utenteLogin.getNome() + "! ---------------------------|");
            }

            while (login && !esci) {
                int sceltaMenu = 0;
                if (utenteLogin.getTipo().equals("Cliente")) {
                    sceltaMenu = menuCliente(scanner);
                } else {
                    sceltaMenu = menuMagazzinieri(scanner);
                }
                if (sceltaMenu == 0) {
                    esci = true;
                    break;
                }
            }
            scanner.close();
        }
    }


    public static Utente creaUtente(int input1, int input2, String emailUtente, String passwordUtente) {
        Scanner scanner = new Scanner(System.in);
        Utente utente;
        System.out.println("Nome:");
        String nome = scanner.nextLine();
        System.out.println("Telefono: (solo numeri)");
        int telefono = scanner.nextInt();
        if (input2 == 1) {
            return new Cliente(nome, emailUtente, telefono, passwordUtente);
        } else if (input2 == 2) {
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
            } else {
                System.out.println("Utente inesistente! Crea un nuovo account!");
            }
        }
        return null;
    }


    //MENU
    public static int menuCliente(Scanner scanner) {
        System.out.println("|---------- 1- Visualizza storico carrello | 2- Carrello Atuale | 3- Lista Prodotti -----------|");
        //Nel carrello atuale deve avere agg e rim prodotto carrello
        //Nella lista Prodotti deve avere tutte le ricerche
        System.out.println("|---------------------------- 4- Modifica dati personale | 0- Esci ----------------------------|");
        return scanner.nextInt();
    }

    public static int menuMagazzinieri(Scanner scanner) {
        System.out.println("|-------------------- 1- Visualizza magazzino | 2- Aggiunge nuovo prodotto --------------------|");
        //Nel Visualizza magazzino deve avere tutte le ricerche
        System.out.println("|---------------------------- 3- Modifica dati personale | 0- Esci ----------------------------|");
        return scanner.nextInt();
    }
}
