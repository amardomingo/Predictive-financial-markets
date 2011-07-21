package BetMarket;

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
	// TODO finish the implementation of the agent
	protected void setup() {
		betmarket = new BetMarket();
		// Behavior to receive messages.
		// This behavior also updates the allowProcess variable in betmarket.
		addBehaviour( new MessageBehavior(receive(), betmarket, this));
	}
}
