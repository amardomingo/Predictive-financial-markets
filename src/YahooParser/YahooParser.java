package YahooParser;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.net.*;
import java.io.*;
import java.text.DateFormat;
import java.util.Calendar;

/*
 * It search thought the yahoo finance services, the requetrsted stock value
 * about certain stock for certain time
 * 
 */
public class YahooParser {

	private String StockName = "";

	/*
	 * Day of the requested stock price
	 */
	private int day = 0;

	/*
	 * Month of the requested stock price
	 */
	private int month = 0;

	/*
	 * Year of the requested stock price
	 */
	private int year = 0;
	
	private double price = 0;

	/*
	 * It's shows if the requested name is a stock or not
	 */

	private boolean isAStockName = false;

	public boolean isAStockName() {
		return isAStockName;
	}

	public void setAStockName(boolean isAStockName) {
		this.isAStockName = isAStockName;
	}

	private String URI = "";

	private double stockPrice = 0;

	/*
	 * It builds the URI for could get the answer from the server with the
	 * requested data.
	 */
	private String buildURI() {
		StringBuilder uri = new StringBuilder();
		uri.append("http://ichart.finance.yahoo.com/table.csv");
		uri.append("?s=").append(StockName);
		uri.append("&a=").append(month - 2);
		uri.append("&b=").append(day);
		uri.append("&c=").append(year);
		uri.append("&d=").append(month);
		uri.append("&e=").append(day);
		uri.append("&f=").append(year);
		uri.append("&g=y");

		return uri.toString();
	}

	private void doCall() throws Exception {

		try {
			URL YahooURI = new URL(buildURI());
			URLConnection ReadingStocksValue = YahooURI.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					ReadingStocksValue.getInputStream()));
			String inputLine;
			int a = 0;
			while ((inputLine = in.readLine()) != null & a < 2) {
				System.out.println(inputLine);
				a++;
			}
			stockPrice = extractPriceAndDate(inputLine);
			in.close();
			isAStockName = true;

		}

		catch (java.io.FileNotFoundException ex) {
			System.out
					.println("La apuesta recibida no se trata de una acción del Ibex 35");
			isAStockName = false;
		}
	}

	/*
	 * It return de stock for the requested value stock
	 */
	public static double extractPriceAndDate(String receivedData) {

		String[] splittedData = receivedData.split(",");
		return Double.parseDouble(splittedData[6]);

	}

	public static int[] ShowDate() {

		// We change the date formatter. We assume that our market agent only
		// uses
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();

		// Using DateFormat format method we can create a string
		// representation of a date with the defined format.
		String reportDate = df.format(today);
		String[] splitted = reportDate.split("-");
		int year = Integer.parseInt(splitted[0]);
		int month = Integer.parseInt(splitted[1]);
		int day = Integer.parseInt(splitted[2]);

		// We assign each number to it correspondent representation (day, month,
		// year)
		int[] DateFormatted = new int[3];
		DateFormatted[0] = day;
		DateFormatted[1] = month;
		DateFormatted[2] = year;

		return DateFormatted;
	}

	public YahooParser(String stockName) throws Exception {
		try {
			this.StockName = IbexConversionName.converseStocks(IbexStock
					.valueOf(stockName.toLowerCase()));
			int[] date = ShowDate();
			this.day = date[0];
			this.month = date[1];
			this.year = date[2];
			doCall();
			System.out.println("The price for " + StockName + " is "
					+ stockPrice);
			this.price = stockPrice;
		} catch (Exception ex) {
			System.out
					.println("La apuesta recibida no se trata de una acción del Ibex 35");
			isAStockName = false;
		}

	}

	public String getStockName() {
		return StockName;
	}

	public void setStockName(String stockName) {
		StockName = stockName;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/*
	public static void main(String args[]) throws Exception {

		YahooParser a = new YahooParser("Iberfrola");
	}
	*/

}
