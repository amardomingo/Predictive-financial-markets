package BetMarket;

import YahooParser.YahooParser;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

/**
 * Agent for the Bet Market
 * 
 * @author Alberto Mardomingo
 * @version 20110721 0.5
 */
public class BetMarketAgent extends Agent {
	
	private BetMarket betmarket;
	
	/**
	 * Setting up the agent.
	 * 
	 */
	protected void setup() {
		betmarket = new BetMarket(this);
		// Behavior to receive messages.
		// This behavior also updates the allowProcess variable in betmarket.
		addBehaviour( new MessageBehavior(receive(), betmarket, this));
	}
}
