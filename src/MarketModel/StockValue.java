package MarketModel;

public class StockValue {

	private double pullValue = 1.7;
	private double bearValue = 1.7;
	
	private double totalInvested = 0;
	private double totalPullInvested = 0;
	private double totalBearInvested = 0;
	
	private int numberInvestors = 0;

	//Getters y setters
	
	public double getPullValue() {
		return pullValue;
	}

	public void setPullValue(double pullValue) {
		this.pullValue = pullValue;
	}

	public double getBearValue() {
		return bearValue;
	}

	public void setBearValue(double bearValue) {
		this.bearValue = bearValue;
	}

	public double getTotalInvested() {
		return totalInvested;
	}

	public void setTotalInvested(double totalInvested) {
		this.totalInvested = totalInvested;
	}

	public double getTotalPullInvested() {
		return totalPullInvested;
	}

	public void setTotalPullInvested(double totalPullInvested) {
		this.totalPullInvested = totalPullInvested;
	}

	public double getTotalBearInvested() {
		return totalBearInvested;
	}

	public void setTotalBearInvested(double totalBearInvested) {
		this.totalBearInvested = totalBearInvested;
	}

	public int getNumberInvestors() {
		return numberInvestors;
	}

	public void setNumberInvestors(int numberInvestors) {
		this.numberInvestors = numberInvestors;
	}
	
	
	
}
