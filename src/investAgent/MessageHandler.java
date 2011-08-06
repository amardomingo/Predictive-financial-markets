package investAgent;

import javax.xml.ws.Response;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.*;
import jade.core.Agent;

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
	
	// The agent.
	private InvestAgent invester;
	
	/**
	 * Constructor
	 * Creates the behavior with the message
	 * 
	 * @param ACLMessage - the message received
	 */
	public MessageHandler(ACLMessage message, InvestAgent invester) {
		this.message = message;
		this.invester = invester;
	}
	
	public void action(){
		String content = message.getContent();
		
		/* Response syntaxes when placing bet.
		 * ActionName/Money/Result/BetCode
		 * Example
		 * Santander/10000/false/121
		 * 
		 * Response syntaxes with bet result:
		 * BetID/result/prize
		 */
		// So, basically, I'll check after a split('/'), if 4 elements, response, else, result
		String[] response = content.split("/");
		
		if(response.length == 4) {
			//TODO: implement something to confirm that the bet has been placed.
			invester.addBetId(content);
		} else if (response.length == 3) {
			//TODO: complete the result handler.
			this.invester.addMoney(Double.parseDouble(response[2]));
			this.invester.removeBetByID(Integer.parseInt(response[0]));
		} else {
			// Houston, we've got a problem.
			System.out.println("Incorrect message syntaxes.");
			System.out.println(content);
		}
		
		block();
	}

}
