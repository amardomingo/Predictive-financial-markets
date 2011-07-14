package BetMarket;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

/**
 * Class to handle the message received by the Bet Market Agent
 * 
 * @author Alberto Mardomingo
 * @version 20110713 0.1
 */
public class MessageBehavior extends CyclicBehaviour{
	
	private boolean processed;
	private ACLMessage message;
	private BetMarket betmarket;
	
	/**
	 * Constructor
	 * 
	 * @param message - The message to handle. 
	 */
	public MessageBehavior (ACLMessage message, BetMarket betmarket){
		this.processed = false;
		this.betmarket = betmarket;
		this.message = message;
	}
	
	/**
	 * Handle the message.
	 * 
	 */
	public void action() {
		// Checks if the message exists, and if the Sender is in the market.
		if (message != null && betmarket.CheckAgent(message.getSender())){
			// TODO implement Message handler.
			// A mesage sintaxis should be defined first.
			/*
			 * Get name and data from the message
			 * 
			 */
			// Temporary variables.
			// Should be extracted from the message
			String name = "";
			double money = 0;
			Prediction pred = Prediction.UP;
			Order order = Order.BET_UP;
			betmarket.MakeBet(name, pred, order, money);
		} else {
			// Not really sure about this.
			block();
		}
	}
}
