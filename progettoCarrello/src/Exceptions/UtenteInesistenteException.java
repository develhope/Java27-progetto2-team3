package Exceptions;

public class UtenteInesistenteException extends Exception{

    public UtenteInesistenteException(){
        super("Questo utente non esiste, oppure non ha effettuato la registrazione");
    }
    public UtenteInesistenteException(String error){ super(error); }
}
