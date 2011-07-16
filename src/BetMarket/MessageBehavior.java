package BetMarket;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

import java.util.Date;

/**
 * Class to handle the message received by the Bet Market Agent
 * 
 * @author Alberto Mardomingo
 * @version 20110714 0.2
 */
public class MessageBehavior extends CyclicBehaviour{
	
	private boolean processed;
	private ACLMessage message;
	private BetMarket betmarket;
	private Agent receiver;
	
	/**
	 * Constructor
	 * 
	 * @param message - The message to handle. 
	 */
	public MessageBehavior (ACLMessage message, BetMarket betmarket, Agent receiver){
		this.processed = false;
		this.betmarket = betmarket;
		this.message = message;
		this.receiver = receiver;
	}
	
	/**
	 * Handle the message.
	 * 
	 */
	public void action() {
		boolean result = false;
		// I'll use this variable twice, so I declare it here
		String[] mesContent = message.getContent().split("/");
		// Checks if the message exists, and if the Sender is in the market.
		if (message != null && betmarket.checkAgent(message.getSender())){
			/*
			 * Current Message sintaxis:
			 * NombreAccion/TipoOrden/DineroInvertido/SentidoOrden/DiasDeInversión/CondicionesEspecialesParaOrdenesLimitadas
			 * Example:
			 * Santander/OrdenLimitada/10000/Alcista/3
			 */
			Date date = new Date();
			
			// If the message sintaxis changes, all this go to hell.
			// Variable moved to the head of the method.
			//String[] mesContent = message.getContent().split("/");
			// Once finished, this variables could be deleted
			// by adding the calls directly in the bet constructor.
			String name = mesContent[0];
			BetType betType = parseBetType(mesContent[1]);
			double money = Integer.parseInt(mesContent[2]);
			Order order = parseOrder(mesContent[3]);
			
			// Creating and placing the bet
			Bet bet = new Bet(name, date, money, order, betType);
			result = betmarket.makeBet(name + receiver.getName(), bet);
			
		} else {
			// Not really sure about this.
			// Probably a trace would be a good idea.
			//block();			
		}
		/*
		 * Response sintaxis:
		 * ActionName/Money/Result
		 * Example
		 * Santander/10000/false
		 */
		// Variable moved to the head of the method.
		//String[] mesContent = message.getContent().split("/");
		String response = mesContent[0] + "/" + mesContent[2] + "/";
		if (result){
			response += "true";
		} else {
			response += "false";
		}
		// Sending the reply
		ACLMessage reply = message.createReply();
		reply.setPerformative( ACLMessage.INFORM );
        reply.setContent(response );
        receiver.send(reply);
	}
	
	// Probably not necesary
	/**
	 * Parse the content of a String from the message,
	 * returning a Prediction.
	 * 
	 * @param content - The string with the part of the message
	 * @return Prediction - The prediction in the message
	 * @deprecated
	 */
	private Prediction parsePrediction(String content){
		Prediction toRet = null;
		if (content == "UP" || content == "Up" || content =="up"){
			toRet = Prediction.UP;
		} else if(content == "down" || content == "Down" || content =="DOWN" ){
			toRet = Prediction.DOWN;
		}	else {
			// Should never get here.
			toRet = Prediction.STAY;
		}
		return toRet;
	}
	
	/**
	 * Parse the content of a string from the message,
	 * returning the type of the bet
	 * 
	 * @param content - the String from the message
	 * @return BetType - the type of the bet
	 */
	private BetType parseBetType(String content){
		BetType toRet = null;
		if (content.contains("Limit") || content.contains("limit") || content.contains("LIMIT")){
			toRet = BetType.LIMITED;
		} else {
			toRet = BetType.MARKET;
		}
		return toRet;
	}
	
	private Order parseOrder(String content){
		Order toRet = null;
		if (content == "UP" || content == "Up" || content =="up"){
			toRet = Order.BET_UP;
		} else {
			toRet = Order.BET_DOWN;
		}
		return toRet;
	}
}
