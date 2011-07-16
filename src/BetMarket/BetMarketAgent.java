package BetMarket;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

/**
 * Agent for the Bet Market
 * 
 * @author Alberto Mardomingo
 * @version 20110714 0.2
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
		addBehaviour( new MessageBehavior(receive(), betmarket, this));
		/*
		 * It would probably be a good idea to add a CyclicBehaviour to update the betmarket.
		 */
		addBehaviour(new UpdateBehavior(betmarket));
		// TODO implement something to update the betmarket.
	}
}
