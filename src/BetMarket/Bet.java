package BetMarket;

import java.util.Calendar;

import jade.core.AID;

/**
 * Class with the bet for the market
 * 
 * @author Alberto Mardomingo
 * @version 20110721 0.5
 */
public class Bet {
	
	/**
	 * The agent making the bet 
	 */
	private AID agentID;
	
	/**
	 * Numeric code to identify the bet.
	 */
	private int code;
	
	// dates syntaxes: Year:Month:Day:Hour:Minute
	/**
	 * The date the bet is placed
	 */
	private String dateAsked;
	
	/**
	 * The date the bet starts
	 */
	private String dateStart;
	
	/**
	 * The duration for the bet, in days.
	 */
	private int days;
	
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
	 * The money placed on the bet
	 */
	private double moneyInv;
	
	/**
	 * The stock name
	 */
	private String stockName;
	
	/**
	 *  The reward ratio
	 */
	private double reward;
	
	/**
	 * Constructor
	 * 
	 * @param name - The name of the bet
	 * @param dateStart - The date the bet should start
	 * @param startValue - The value for the start of the bet
	 * @param betOrder - The order to the market
	 * @param betType - The type of the bet
	 */
	public Bet(int code, String dateStart, double startValue, Order betOrder, BetType betType, AID agentID, double moneyInv, String stockName){
		this.code = code;
		this.startValue = startValue;
		this.betOrder = betOrder;
		this.betType = betType;
		this.dateStart = dateStart;
		this.agentID = agentID;
		this.moneyInv = moneyInv;
		this.stockName = stockName;
		// The date the bet was asked.
		this.dateAsked = Calendar.YEAR + ":" + Calendar.MONTH + ":" + Calendar.DATE + ":" + Calendar.HOUR + ":" + Calendar.MINUTE;
		// default value of 1.02 to reward
		this.reward = 1.02;
		// default value: right away
		this.setDays(0);
	}
	
	/**
	 * Getter
	 * 
	 * @return code of the bet
	 */
	public int getCode(){
		return this.code;
	}
	
	/**
	 * Getter
	 * 
	 * @return when the bet was placed
	 */
	public String getDateAsked(){
		return this.dateAsked;
	}
	
	/**
	 * Getter
	 * 
	 * @return when the bet should start
	 */
	public String getDateStart(){
		return this.dateStart;
	}
	
	/**
	 * Setter
	 * 
	 * @param String - the time the bet is process
	 */
	public void setDateStart(String date){
		this.dateStart = date;
	}
	
	/**
	 * Setter
	 * Sets the duration of the bet
	 * 
	 * @param days - the duration in days.
	 */
	public void setDays(int days) {
		this.days = days;
	}
	
	/**
	 * Getter
	 * 
	 * @return int - the number of days
	 */
	public int getDays() {
		return days;
	}

	/**
	 * Getter
	 * 
	 * @return StockValue with the money to start the bet
	 */
	public double getStartValue(){
		return this.startValue;
	}
	/**
	 * Getter
	 * 
	 * @param StockValue with the end value of the bet
	 */
	public void setEndValue(double endValue) {
		this.endValue = endValue;
	}
	
	/**
	 * Setter
	 * 
	 * @return StockValue with the end value of the bet
	 */
	public double getEndValue() {
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
	public void setBetDone(boolean betDone) {
		this.betDone = betDone;
	}
	
	/**
	 * Setter
	 * 
	 * @return true if the bet is complete
	 */
	public boolean getBetDone() {
		return betDone;
	}
	
	/**
	 * Getter
	 * 
	 * @return the AID of the agent making the bet.
	 */
	public AID getAgentID(){
		return this.agentID;
	}
	
	/**
	 * Getter
	 * 
	 * @return double - the money placed in the bet
	 */
	public double getMoney(){
		return this.moneyInv;
	}
	
	/**
	 * Setter
	 * Changes the stock name for the bet
	 * 
	 * @param stockName - the new name
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	/**
	 * Getter
	 * 
	 * @return String - the stock name for the bet
	 */
	public String getStockName() {
		return stockName;
	}
	
	/**
	 * Setter
	 *  
	 * @param reward - the new reward ratio.
	 */
	public void setReward(double reward) {
		this.reward = reward;
	}
	
	/**
	 * Getter
	 * 
	 * @return double - the current reward ratio
	 */
	public double getReward() {
		return reward;
	}
}
