package BetMarket;

import jade.core.Agent;
import jade.core.AID;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Class to represent the Market.
 * 
 * @author Alberto Mardomingo
 * @version 20110714 0.2
 */
public class BetMarket {
	
	// Hashmap with all the agents in the market
	private HashMap<AID,Integer> agents = new HashMap<AID,Integer>();
	
	// Hashmap with the name of the bet and the bet Itself.
	private HashMap<String, Bet> bets = new HashMap<String, Bet>();
	
	/**
	 * Check if there is a bet with the same name
	 * 
	 * @param name - the name of the bet.
	 * @return true if the name is in use.
	 */
	public boolean existBet(String name){
		return bets.containsKey(name);
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
	public boolean makeBet(String name, Bet bet){
		boolean result = false;
		if (!bets.containsKey(name)){
			// Adds the bet to the market
			bets.put(name, bet);
			result = true;
			System.out.println("Bet " + name + " added to the market.");
		} else {
			System.out.println("The bet " + name + "already exists in the market.");
		}
		return result;
	}
	
	/**
	 * Checks if the agent has money enough to proceed with the bet
	 * 
	 * @param agent - The agent to check 
	 * @param cost - The cost of the bet
	 * @return true if he has money enough
	 */
	public boolean gotCapital(AID agentID, int cost){
		return (cost <= agents.get(agentID));
	}
	
	/**
	 * Adds an agent to the bet market
	 * 
	 * @param agentID - The ID of the agent to add
	 * @param money - The money of the agent.
	 * @return true if added
	 */
	public boolean addAgent(AID agentID, int money){
		boolean success = false;
		if (!checkAgent(agentID)){
			agents.put(agentID, money);
			success = true;
		}
		return success;
	}
	/**
	 * Check if the agent already exists
	 * 
	 * @param agentID - The ID of the agent to check
	 * @return true it the agent exists
	 */
	public boolean checkAgent(AID agentID){
		return agents.containsKey(agentID);
	}
	
	/**
	 * Execute all the bets queued after the market is closed
	 * 
	 * @return true if everything goes well.
	 */
	public boolean runBets(){
		boolean result = false;
		//Iterator iter = bets.entrySet().iterator();
		Set<String> betsNames = bets.keySet();
		Iterator<String> iter = betsNames.iterator();
		while (iter.hasNext()){
			String betName = (String)iter.next();
			Bet thisBet = bets.get(betName);
			//TODO run the Bet.
		}
		return result;	
	}
}
