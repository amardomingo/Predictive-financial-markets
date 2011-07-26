package investAgent;

import jade.core.Agent;

import BetMarket.BetType;
import BetMarket.Order;

/**
 * Agent to perform the bets
 * 
 * @author Alberto Mardomingo
 * @version 20110724 0.1
 */
public class InvestAgent extends Agent{
	//TODO implement InvestAgent
	
	protected void setup(){
		// Behavior to send messages
		addBehaviour(new MessageHandler(receive()));
	}
	
	/**
	 * Sends the bet to the market
	 * 
	 * @return true if everything goes fine.
	 */
	private boolean makeBet(){
		boolean result = false;
		//TODO implement something to send the bet.
		/*
		 * Current Message syntaxes:
		 * NombreAccion/TipoOrden/DineroInvertido/SentidoOrden/DiasDeInversión/CondicionesEspecialesParaOrdenesLimitadas
		 * Example:
		 * Santander/OrdenLimitada/10000/Alcista/3
		 */
		// Al this variables should be got from the data or so.
		String StockName = "";
		BetType betType = BetType.MARKET;
		Order order = Order.BET_DOWN;
		double money = 0;
		int time = 0;
		
		return result;
	}
}
