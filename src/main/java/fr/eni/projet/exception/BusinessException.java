package fr.eni.projet.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception{

    private static final long serialVersionUID = 1L;

    private List<String> exceptionMessages;

   public BusinessException() {
       this.exceptionMessages = new ArrayList<String>();
   }

   public Iterable<String> getExceptionMessages(){
       return exceptionMessages;
   }
   public void add (String message) {
       this.exceptionMessages.add(message);
   }

   public boolean hasError() {
       return  !this.exceptionMessages.isEmpty();
   }
}