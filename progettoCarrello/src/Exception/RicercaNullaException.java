<<<<<<<< HEAD:progettoCarrello/src/Exceptions/RicercaNullaException.java
package Exceptions;
========
package Exception;
>>>>>>>> 2d0ddd304e861e4298139014b40afd151976f680:progettoCarrello/src/Exception/RicercaNullaException.java

public class RicercaNullaException extends Exception{
    public RicercaNullaException(){
        super("Non esiste prodotto da visualizzare!");
    }
    public RicercaNullaException(String message){
        super(message);
    }
}
