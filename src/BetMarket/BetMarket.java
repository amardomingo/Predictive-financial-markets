package BetMarket;

import jade.core.Agent;
import jade.core.AID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import YahooParser.YahooParser;

/**
 * Class to represent the Market.
 * 
 * @author Alberto Mardomingo
 * @version 20110721 0.5
 */
public class BetMarket {
	
	// Opening time for the betmarket
	public static final int OPEN = 900;
	
	// Closing time 
	public static final int CLOSE = 1800;
	
	// Both integer to simplify comparisons.
	
	// List with all the bets;
	private ArrayList<Bet> bets;
	
	// true if the bet can be process immediately
	private boolean allowProcess;
	
	// Integer to control the number of bets.
	private int counter;
	
	/**
	 * Constructor
	 * Creates the bet market, with all the parameters empty.
	 *  
	 */
	public BetMarket(){
		this.bets = new ArrayList<Bet>();
		this.allowProcess = false;
		this.counter = 0;
	}
	/**
	 * Check if there is a bet with the same name
	 * 
	 * @param name - the name of the bet.
	 * @return true if the name is in use.
	 */
	public boolean existBet(int code){
		boolean result = false;
		for(Bet bet : bets){
			if (bet.getCode() == code) result = true;
		}
		return result;
	}

	/**
	 * Makes the bet with the given parameters
	 * 
	 * @param name - The name of the bet
	 * @param prediction - The prediction for it 
	 * @param order - The order to the market.
	 * @param inverted - The money to invert.
	 * @return true if the bet was placed correctly
	 */
	public boolean makeBet(Bet bet){
		boolean result = false;
		if (existBet(bet.getCode())){
			if (allowProcess) {
				// Runs the bet right away
				runBet(bet);
				result = true;
				System.out.println("The bet " + bet.getCode() + " has been realized.");
			} else {
				// Adds the bet to the market
				bets.add(bet);
				result = true;
				System.out.println("Bet " + bet.getCode() + " added to the market.");
			}
		} else {
			System.out.println("The bet " + bet.getCode() + "already exists in the market.");
		}
		return result;
	}
	
	/**
	 * Execute all the bets queued after the market is closed
	 * 
	 * @return true if everything goes well.
	 */
	public boolean runBets(){
		boolean result = false;
		for (Bet bet: bets){
			double prize = runBet(bet);
			//After running the bet, delete it
			bets.remove(bet);
		}
		bets.clear(); // Just in case
		return result;	
	}
	
	/**
	 * Method to run each single bet
	 * 
	 * @param Bet - the bet to run
	 * @return true if everything goes well
	 */
	public double runBet(Bet bet){
		double prize = 0;
		try {
			YahooParser parser = new YahooParser(bet.getStockName());
			bet.setEndValue(parser.getStockPrice());
			// Check if the value rises.
			boolean valueUP = bet.getStartValue() < bet.getEndValue();
			Order order = bet.getBetOrder();
			if ((order.equals(Order.BET_UP) && valueUP || (order.equals(Order.BET_DOWN) && !valueUP))){
				// WIN
				// TODO: change this to the real ratio
				double ratio = bet.getReward();
				prize = ratio * bet.getMoney();
			}
		} catch(Exception e) {
			//TODO handle exception in runBet.
		}
		return prize;
	}
	
	/**
	 * Setter
	 * 
	 * @param status - true if the bets can be processed immediately
	 */
	public void setAllowProcess(boolean status) {
		this.allowProcess = status;
	}
	
	/**
	 * Getter
	 * 
	 * @return true if the bets can be processed immediately
	 */
	public boolean getAllowProcess(){
		return this.allowProcess;
	}
	/**
	 * Generates a new code for a bet.
	 * 
	 * @return the code for the bet. 
	 */
	public int generateCode(){
		this.counter++;
		return counter;
	}
}
