package jac444wk1.q5;
import java.util.*;
import java.time.LocalDateTime; 
/**
 * @author Galina Erostenko
 *
 */
public class Ticket {
    public LocalDateTime date; 
    public ArrayList<Integer> id;
    
    /**
     * Ticket Constructor
     * @param _ticket
     */
    public Ticket(ArrayList<Integer> _ticket){
        date = LocalDateTime.now();
        id = _ticket;
    }
}
