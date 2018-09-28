package jac444wk1.q5;
import java.util.*;
/**
 * @author Galina Erostenko
 *
 */
public class Player {
    private double balance;
    private Ticket ticket;
    private ArrayList<Ticket> ticketHistory;

    /**
     * Player Constructor
     * @param _balance
     */
    public Player(double _balance){
        balance = _balance;
        ticket = null;
        ticketHistory = new ArrayList<Ticket>();
    }

    /**
     * @return balance
     */
    public double getBalance(){
        return balance;
    }

    /**
     * @param money
     */
    public void updateBalance(double money) {
        balance += money;
    }

    /**
     * @return ticket
     */
    public Ticket showTicket(){
        return ticket;
    }

    /**
     * @param _ticket
     */
    public void setTicket(Ticket _ticket) {
        ticket = _ticket;
        ticketHistory.add(_ticket);
    }
}
