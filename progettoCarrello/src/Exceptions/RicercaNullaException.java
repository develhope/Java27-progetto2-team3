package Exceptions;

public class RicercaNullaException extends Exception{
    public RicercaNullaException(){
        super("Non esiste prodotto da visualizzare!");
    }
    public RicercaNullaException(String message){
        super(message);
    }
}
