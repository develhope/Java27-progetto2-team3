import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean esci = false;
        while (!esci) {
            boolean login = false;
            List<Utente> utenteList = new ArrayList<>();
            Utente utenteLogin = null;
            while (!login) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("|---------------------------------- BENVENUTI AL MERCATOTECH ----------------------------------|");
                System.out.println("|-----------------------------  1- LogIn | 2- SignOut | 3- Exit  ------------------------------|");
                int input1 = scanner.nextInt();
                if (input1 == 1 || input1 == 2) {
                    System.out.println("|------------------------------   1- Cliente | 2- Magazziniere  -------------------------------|");
                    int input2 = scanner.nextInt();
                    if (!((input2 == 1) || (input2 == 2))) {
                        System.out.println("Tipo Utente invalido!");
                    } else {
                        System.out.println("E-mail:");
                        String emailUtente = scanner.nextLine();
                        System.out.println("Password:");
                        String passwordUtente = scanner.nextLine();

                        if (input1 == 1) {
                            for (Utente i : utenteList) {
                                if (i.getEmail().equals(emailUtente)) {
                                    if (i.getPassword().equals(passwordUtente)) {
                                        utenteLogin = i;
                                        login = true;
                                    } else {
                                        System.out.println("Password errata!");
                                    }
                                } else {
                                    System.out.println("Utente inesistente! Crea un nuovo account!");
                                }
                            }
                        } else if (input1 == 2) {
                            System.out.println("Nome:");
                            String nome = scanner.nextLine();
                            System.out.println("Telefono: (solo numeri)");
                            int telefono = scanner.nextInt();
                            if (input2 == 1) {
                                utenteList.add(new Cliente(nome, emailUtente, telefono, passwordUtente));
                            } else if (input2 == 2) {
                                utenteList.add(new Magazziniere(nome, emailUtente, telefono, passwordUtente));
                            }
                        }
                    }
                } else if (input1 == 3) {
                    esci = true;
                } else {

                    System.out.println("input invalido!");
                }
                scanner.close();
            }

            while (login && esci) {
                System.out.println("|----------------------------- Benvenuto " + utenteLogin.getNome() + "! ---------------------------|");
                Scanner scanner = new Scanner(System.in);
                if (utenteLogin.getTipoUtente().equals("Cliente")) {
                    System.out.println("|---------- 1- Visualizza storico carrello | 2- Carrello Atuale | 3- Lista Prodotti -----------|");
                    System.out.println("|---------------------------- 4- Modifica dati personale | 0- Esci ----------------------------|");
                } else {
                    System.out.println("|---------- 1- Visualizza magazzino | 2- Aggiunge nuovo prodotto | 3- Lista Prodotti ----------|");
                    System.out.println("|---------------------------- 4- Modifica dati personale | 0- Esci ----------------------------|");
                }
            }
        }
    }
}
