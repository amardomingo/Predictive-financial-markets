package BetMarket;

import jade.core.Agent;
import jade.core.AID;
import jade.tools.sniffer.Message;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Calendar;
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
	
	// The agent using this bet market
	private BetMarketAgent agent;
	
	/**
	 * Constructor
	 * Creates the bet market, with all the parameters empty.
	 *  
	 *  @param BetMarketAgent - the agent using this bet market.
	 */
	public BetMarket(BetMarketAgent agent){
		this.agent = agent;
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
		// bets.clear(); // Not a good idea.
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
			if (checkDate(bet)){
				YahooParser parser = new YahooParser(bet.getStockName());
				bet.setEndValue(parser.getStockPrice());
				// Check if the value rises.
				boolean valueUP = bet.getStartValue() < bet.getEndValue();
				Order order = bet.getBetOrder();
				if ((order.equals(Order.BET_UP) && valueUP || (order.equals(Order.BET_DOWN) && !valueUP))){
					// WIN
					double ratio = bet.getReward();
					prize = ratio * bet.getMoney();
				}
				ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
				/*
				 * Response syntaxes:
				 * BetID/result/prize
				 */
	
				String result = "" + bet.getCode();
				if (prize > 0) {
					result += "/win/" + prize;
				} else {
					result += "/false/0";
				}
				msg.setContent(result);
				msg.addReceiver(bet.getAgentID());
				this.agent.send(msg);
			}
		} catch(Exception e) {
			System.out.println("Something went wrong when procesing the bet " + bet.getCode() + "in " + bet.getStockName());
			System.out.println(e.getMessage());
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
	
	/**
	 * Check if the bet can be processed
	 * according to the time it was supposed to last
	 * 
	 * @param Bet - the bet to check
	 */
	private boolean checkDate(Bet bet){
		boolean result = false;
		//TODO: complete this method, adding the year and improving the months change.
		// dates syntaxes: Year:Month:Day:Hour:Minute
		String[] startDate = (bet.getDateStart()).split(":");
		int startMonth = Integer.parseInt(startDate[1]);
		int startDay= Integer.parseInt(startDate[2]);
		int currentDay = Calendar.DATE;
		int currentMonth = Calendar.MONTH;
		if (startMonth == currentMonth){
			result = (currentDay - startDay) >= bet.getDays();
		} else {
			result = (currentDay - startDay + 30) >= bet.getDays();
		}
		return result;
	}
}
