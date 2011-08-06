package investAgent;

import java.util.List;
import java.util.ArrayList;

import jade.core.Agent;

import BetMarket.BetType;
import BetMarket.Order;
import BetMarket.Bet;

/**
 * Agent to perform the bets
 * 
 * @author Alberto Mardomingo
 * @version 20110724 0.1
 */
public class InvestAgent extends Agent{
	//TODO implement InvestAgent
	
	/**
	 * The money 
	 */
	private double money;
	
	/**
	 * The bets
	 */
	private List<Bet> bets;
	
	protected void setup(){
		// Agent parameters.
		Object[] args = getArguments();
		bets = new ArrayList<Bet>();
		if (args != null){
			// The first parameter is the money of the agent.
			money = Double.parseDouble((String)args[0]);
		}
		// Behavior to send messages
		addBehaviour(new MessageHandler(receive(), this));
		addBehaviour(new BetBehavior(this));
	}
	
	/**
	 * Sends the bet to the market
	 * 
	 * @return true if everything goes fine.
	 * @deprecated
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
		
		String message = StockName + "/";
		
		return result;
	}
	
	/**
	 * Getter
	 * Returns the money of the agent
	 * 
	 * @returns double with the money
	 */
	public double getMoney(){
		return this.money;
	}
	
	/**
	 * Setter
	 * Sets a new amount of money for the agent.
	 * 
	 * @param double money - the new amount of money.
	 */
	public void setMoney(double money){
		this.money = money;
	}
	
	/**
	 * Adds money to the account
	 * 
	 * @param double - the money to add
	 * @return double - the total amount of money
	 */
	public double addMoney(double added) {
		this.money += added;
		return this.money;
	}
	
	/**
	 * Returns the corresponding bet for the given id.
	 * Null if no bet matches the id
	 * 
	 * @param int id -  the bet id
	 * @return bet - the bet
	 */
	public Bet getBetByID(int id){
		Bet result = null;
		for (Bet bet: bets) {
			if (bet.getCode() == id){
				result = bet;
			}
		}
		return result;
	}
	
	/**
	 * Adds a bet to the list
	 * 
	 * @param bet - the bet to add
	 */
	public void addBet(Bet bet){
		this.bets.add(bet);
	}
	
	/**
	 * Updates a bet, with the one given
	 * 
	 * @param bet - the new bet
	 * @return true -  if everything is correct
	 */
	public boolean updateBet(Bet bet){
		boolean result = false;
		for (Bet betIn:bets){
			if (betIn.getCode() == bet.getCode()) {
				int i = bets.indexOf(betIn);
				bets.set(i, bet);
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * After a bet is created, and a response is received,
	 * updates the list, adding the id to the bet.
	 * The bet is located with the confirmation received.
	 * ActionName/Money/Result/BetCode
	 * 
	 * @param string - the bet data
	 * @return true if the id is correctly set
	 */
	public boolean addBetId(String betData){
		boolean result = false;
		String[] data = betData.split("/");
		String name = data[0];
		int money = Integer.parseInt(data[1]);
		int betCode = Integer.parseInt(data[2]);
		for(Bet bet: bets){
			if( bet.getStockName().equals(name) && money == bet.getMoney()){
				bet.setCode(betCode);
			}
		}
		return result;
	}
	
	/**
	 * Remove the bet, searching by the id of the bet
	 * 
	 * @param id - the id of the bet to remove
	 */
	public void removeBetByID(int id){
		for(Bet bet:bets){
			if (bet.getCode() == id){
				this.bets.remove(bet);
			}
		}
	}
}
