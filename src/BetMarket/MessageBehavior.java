package BetMarket;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

import java.util.Calendar;

import YahooParser.YahooParser;

import MarketModel.*;

/**
 * Class to handle the message received by the Bet Market Agent
 * 
 * @author Alberto Mardomingo
 * @version 20110721 0.5
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
		// Checks if the bets can be processed right away.
		betmarket.setAllowProcess(checkTime());
		
		boolean result = false;
		// I'll use this variable twice, so I declare it here
		String[] mesContent;
		// Checks if the message exists, and if the Sender is in the market.
		if (message != null){
			try {
			/*
			 * Current Message syntaxes:
			 * NombreAccion/TipoOrden/DineroInvertido/SentidoOrden/DiasDeInversión/CondicionesEspecialesParaOrdenesLimitadas
			 * Example:
			 * Santander/OrdenLimitada/10000/Alcista/3
			 */
				mesContent = message.getContent().split("/");
				// The date
				// Syntaxes: Year:Month:Day:Hour:Minute
				String date = Calendar.YEAR + ":" + Calendar.MONTH + ":" + Calendar.DATE + ":" + Calendar.HOUR + ":" + Calendar.MINUTE;
			
				// If the message syntaxes changes, all this go to hell.
				String name = mesContent[0];
				BetType betType = parseBetType(mesContent[1]);
				double money = Integer.parseInt(mesContent[2]);
				Order order = parseOrder(mesContent[3]);
			
				// Creating and placing the bet
				int code = betmarket.generateCode();
				Bet bet = null;
				// Should probably check this.
				YahooParser parser = new YahooParser(name);
				StockValue val = MarketModel.Bet(order, money, name);
			
				bet = new Bet(code, date, parser.getStockPrice(), order, betType, message.getSender(), money, name);
				result = betmarket.makeBet(bet);
				
				// Send a response
				/* Response syntaxes:
				 * ActionName/Money/Result
				 * Example
				 * Santander/10000/false
				 */
				
				String response = mesContent[0] + "/" + mesContent[2] + "/";
				response += result;
				// Sending the reply
				ACLMessage reply = message.createReply();
				reply.setPerformative( ACLMessage.INFORM );
		        reply.setContent(response );
		        receiver.send(reply);
			
			} catch(Exception e) {
				// Send a message informing something goes wrong
				ACLMessage reply = message.createReply();
				reply.setPerformative( ACLMessage.INFORM );
		        reply.setContent("Wrong.ExceptionMessage");
		        receiver.send(reply);
				
				System.out.println("Something went terribly wrong. Got an exception procesing a message:");
				System.out.println("Message: " + message.getContent());
				System.out.println(e.getMessage());
			}
		} else {
			// Not really sure about this.
			// Probably a trace would be a good idea.
		}
		block();
	}
	
	// Probably not necessary
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
	
	/**
	 * Parse the order in the message
	 * 
	 * @param content - the correct part of the message
	 * @return Order - the order itself.
	 */
	private Order parseOrder(String content){
		Order toRet = null;
		if (content == "UP" || content == "Up" || content =="up"){
			toRet = Order.BET_UP;
		} else {
			toRet = Order.BET_DOWN;
		}
		return toRet;
	}
	
	/**
	 * Updates the time for the betmarket
	 * 
	 * @return true if the bet can be process immediately
	 */
	private boolean checkTime(){
		int time = Calendar.HOUR*100 + Calendar.MINUTE;
		return (time >= BetMarket.OPEN && time <= BetMarket.CLOSE);
	}
}
