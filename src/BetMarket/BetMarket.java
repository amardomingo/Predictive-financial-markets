package BetMarket;

import jade.core.Agent;

/**
 * Class to represent the Market.
 * 
 * @author Alberto Mardomingo
 * @version 20110709 0.1
 */
public class BetMarket {

	/**
	 * Check if there is a ber with the same name
	 * 
	 * @param name - the name of the bet.
	 * @return true if the name is in use.
	 */
	public boolean ExistBet(String name){
		//TODO implement ExistBet
		return true;
	}

	/**
	 * Makes the bet with the given parameters
	 * 
	 * @param name - The name of the bet
	 * @param prediction - The prediction for it 
	 * @param order - The order 
	 * @param inverted - The money to invert.
	 */
	// TODO Complete MakeBet doc
	public void MakeBet(String name, Prediction prediction, Order order, Double inverted){
		//TODO implement MakeBet
	}
	
	/**
	 * Checks if the agent has money enough to proceed with the bet
	 * 
	 * @param agent - The agent to check 
	 * @return true if he has money enough
	 */
	public boolean GotCapital(Agent agent){
		//TODO implement GotCapital
		return false;
	}
}
