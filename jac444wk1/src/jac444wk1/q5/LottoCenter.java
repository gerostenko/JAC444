package jac444wk1.q5;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Galina Erostenko
 *
 */
public class LottoCenter {
    public final double firstPlace = 0.5;
    public final double secondPlace = 0.35;
    public final double thirdPlace = 0.15;

    private double bank;
    public double ticketCost;

    public double totalPrize;
    public double firstPrize;
    public double secondPrize;
    public double thridPrize;

    public Ticket drawTicket;
    private ArrayList<Ticket> drawTickets;

    /**
     * LottoCenter Constructor
     */
    LottoCenter(){
        bank = 1000000.00;
        totalPrize = 1000;
        ticketCost = 100;
        drawTickets = new ArrayList<Ticket>();
    }
    
    /**
     * Nested Class Generator
     */
    private static class Generator {
        public ArrayList<Integer> randomNumbers;
        /**
         * Generator Constructor
         */
        private Generator() {
            ArrayList<Integer> randomNums = new ArrayList<Integer>();
            while (randomNums.size() < 3) {
                int random = ThreadLocalRandom.current().nextInt(1, 11); //maxNum is exclusive
                while (!randomNums.contains(random)){
                    randomNums.add(random);
                }
            }
            randomNumbers = randomNums;
        }
    }

    /**
     * purchaseTicket func updated player's balance, generates ticker for player, updates the bank, stores the ticket
     * @param player
     */
    public void purchaseTicket(Player player){
        if (player.getBalance() > ticketCost) {
            player.updateBalance(-ticketCost);
            bank += ticketCost;
            Ticket generatedTicket = new Ticket(new Generator().randomNumbers);
            player.setTicket(generatedTicket);
            drawTickets.add(generatedTicket);
        }
    }

    /**
     * checkPlayersTicket func checks the ticket and update the balances respectively
     * @param player
     */
    public void checkPlayersTicket(Player player){
        double winning = 0;
        int result = checkTicket(player.showTicket(), drawTicket);
        
        if (result == 1) 
        	winning = firstPrize;
        else if (result == 2) 
        	winning = secondPrize;
        else if (result == 3)
        	winning = thridPrize;
        
        player.updateBalance(winning);
        bank -= winning;
    }

    /**
     * doTheDraw func generates the ticket for the draw and calculates the prizes
     */
    public void doTheDraw(){
        drawTicket = new Ticket(new Generator().randomNumbers);
        calculatePrizes(drawTicket);
    }
    
    /**
     * cleanUp func resets all the used vars
     */
    public void cleanUp(){
        drawTicket = null;
        firstPrize = 0;
        secondPrize = 0;
        thridPrize = 0;
        drawTickets.clear();
    }
    
    /**
     * calculatePrizes func checks how many tickets win 1st, 2nd and 3rd prizes, updates the prizes amounts based on 
     * the number of winners
     * @param draw
     */
    private void calculatePrizes(Ticket draw){
        int numberOfFirstPrizeWinners = 0;
        int numberOfSecondPrizeWinners = 0;
        int numberOfThirdPrizeWinners = 0;
        for(Ticket ticket : drawTickets) {
        	int result = checkTicket(ticket, draw);
        	if (result == 1) 
        		numberOfFirstPrizeWinners++;
        	else if(result == 2) 
        		numberOfSecondPrizeWinners++;
        	else if (result == 3)
        		numberOfThirdPrizeWinners++;
        }
        firstPrize = numberOfFirstPrizeWinners != 0 ? (totalPrize*firstPlace)/numberOfFirstPrizeWinners : 
        	totalPrize*firstPlace;
        secondPrize = numberOfSecondPrizeWinners != 0 ? (totalPrize*secondPlace)/numberOfSecondPrizeWinners :
        	totalPrize*secondPlace;
        thridPrize = numberOfThirdPrizeWinners != 0 ? (totalPrize* thirdPlace)/numberOfThirdPrizeWinners :
        	totalPrize * thirdPlace;
    } 

    /**
     * checkTicket func checks how many digits from player's ticket match the draw ticket and returns the respective result
     * All digits match = 1, two digits match = 2, one digit = 1, else = 0
     * @param ticket
     * @param draw
     * @return
     */
    private int checkTicket(Ticket ticket, Ticket draw) {
        int counter = 0;
        for(int i = 0; i < 3; i++) {
            if (draw.id.contains(ticket.id.get(i)))
                counter++;
        }
        return counter == 3 ? 1 : counter == 2 ? 2 : counter == 1 ? 3 : 0;
    }

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) { 
    	ArrayList<Player> players = new ArrayList<Player>();
    	players.add(new Player(10000));
    	players.add(new Player(10500));
    	players.add(new Player(9400));
    	players.add(new Player(8750));
    	players.add(new Player(13000));
    	players.add(new Player(11000));
    	players.add(new Player(8400));
    	players.add(new Player(8745));
    	players.add(new Player(7856));
    	players.add(new Player(6874));
    	
    	LottoCenter lottoCenter = new LottoCenter();
    	
    	System.out.print("Starting balances");
    	System.out.println();
    	for (int i = 0; i < 10; i++) {
    		System.out.printf("Player %d Balance: %.2f", i, players.get(i).getBalance());
    		System.out.println();
    	}
    	
    	for (int i = 0; i < 50; i++) {
    		for (Player player : players) {
        		lottoCenter.purchaseTicket(player);
        	}
    		lottoCenter.doTheDraw();
        	for (Player player: players) {
        		lottoCenter.checkPlayersTicket(player);
        	}
        	lottoCenter.cleanUp();
    	}
    	
    	System.out.println();
    	System.out.print("Ending balances");
    	System.out.println();
    	for (int i = 0; i < 10; i++) {
    		System.out.printf("Player %d Balance: %.2f", i, players.get(i).getBalance());
    		System.out.println();
    	}
    }
}
