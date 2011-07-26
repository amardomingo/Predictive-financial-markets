package investAgent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.*;

/**
 * Message Handler to receive the messages
 * with the bet results from the market
 * 
 * @author Alberto Mardomingo
 * @version 20110725 0.1 
 */
public class MessageHandler extends CyclicBehaviour {
	
	// The message received
	private ACLMessage message;
	
	/**
	 * Constructor
	 * Creates the behavior with the message
	 * 
	 * @param ACLMessage - the message received
	 */
	public MessageHandler(ACLMessage message) {
		this.message = message;
	}
	
	public void action(){
		//TODO implement message handler for the agent
		block();
	}

}
