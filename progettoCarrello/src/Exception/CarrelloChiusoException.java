<<<<<<<< HEAD:progettoCarrello/src/Exceptions/CarrelloChiusoException.java
package Exceptions;
========
package Exception;
>>>>>>>> 2d0ddd304e861e4298139014b40afd151976f680:progettoCarrello/src/Exception/CarrelloChiusoException.java

public class CarrelloChiusoException extends Exception{
    public CarrelloChiusoException(){
        super("Questo carrello non può più essere modificato poiché è già stato finalizzato");
    }
    public CarrelloChiusoException(String error){
        super(error);
    }
}
