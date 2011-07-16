package BetMarket;

import java.util.Date;

/**
 * Class with the bet for the market
 * 
 * @author Alberto Mardomingo
 * @version 20110715 0.1
 */
public class Bet {
	
	/**
	 * The name of the bet
	 */
	private String name;
	
	/**
	 * The date the bet is placed
	 */
	private Date dateAsked;
	
	/**
	 * The date the bet starts
	 */
	private Date dateStart;
	
	/**
	 * The initial Value for the bet
	 */
	private double startValue;
	/**
	 * The final value for the bet
	 */
	private double endValue;
	
	/**
	 * if true, the bet is complete
	 */
	private boolean betDone;
	
	/**
	 * The order for the bet
	 */
	private Order betOrder;
	
	/**
	 * The type of the bet.
	 */
	private BetType betType;
	
	/**
	 * Constructor
	 * 
	 * @param name - The name of the bet
	 * @param dateStart - The date the bet should start
	 * @param startValue - The value for the start of the bet
	 * @param betOrder - The order to the market
	 * @param betType - The type of the bet
	 */
	public Bet(String name, Date dateStart, double startValue, Order betOrder, BetType betType ){
		this.name = name;
		this.startValue = startValue;
		this.betOrder = betOrder;
		this.betType = betType;
		this.dateStart = new Date();
	}
	
	/**
	 * Getter
	 * 
	 * @return string with the name of the bet.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Getter
	 * 
	 * @return when the bet was placed
	 */
	public Date getDateAsked(){
		return this.dateAsked;
	}
	
	/**
	 * Getter
	 * 
	 * @return when the bet should start
	 */
	public Date getDateStart(){
		return this.dateStart;
	}
	
	/**
	 * Getter
	 * 
	 * @return double with the money to start the bet
	 */
	public double getStartValue(){
		return this.startValue;
	}
	/**
	 * Getter
	 * 
	 * @param double with the end value of the bet
	 */
	private void setEndValue(double endValue) {
		this.endValue = endValue;
	}
	
	/**
	 * Setter
	 * 
	 * @return double with the end value of the bet
	 */
	private double getEndValue() {
		return endValue;
	}

	/**
	 * Getter
	 * 
	 * @return the order for the bet
	 */
	public Order getBetOrder(){
		return this.betOrder;
	}
	
	/**
	 * Getter
	 * 
	 * @return the type of the bet
	 */
	public BetType getBetType(){
		return this.betType;
	}
	/**
	 * Getter
	 * 
	 * @param bolean - the bet is complete.
	 */
	private void setBetDone(boolean betDone) {
		this.betDone = betDone;
	}
	
	/**
	 * Settet
	 * 
	 * @return true if the bet is complete
	 */
	private boolean getBetDone() {
		return betDone;
	}
}
