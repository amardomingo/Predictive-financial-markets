package BetMarket;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

import java.util.Date;

/**
 * Behaviour to update the Bet Market
 * 
 * @author Alberto Mardomingo
 * @version 20110716 0.1
 */
public class UpdateBehavior extends CyclicBehaviour {
	
	private BetMarket betmarket;
	
	public UpdateBehavior(BetMarket betmarket){
		this.betmarket = betmarket;
	}
	
	public void action(){
		betmarket.runBets();
		// Basic implementation
		// TODO: complete the UpdateBehavior implementation.
	}

}
