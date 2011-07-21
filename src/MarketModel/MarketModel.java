package MarketModel;

import java.util.HashMap;

import BetMarket.Order;

public class MarketModel {
	
	
/*
 * The average of the total of diary bets from each stock (in euros)
 * It should be a method for calibrate and obtain this values before start
 * calculating or making bets.
 * This value 10000 has no sense, its for make test. When he receive a database
 * with all inverstor agents, we can create a method for fill it properly.
 */
	
private static StockValue ABG;
private static StockValue ABE;
private static StockValue ACX;
private static StockValue ACS;
private static StockValue ANA;
private static StockValue AMS;
private static StockValue MTS;
private static StockValue BKT;
private static StockValue BBVA;
private static StockValue BME;
private static StockValue CABL;
private static StockValue EBRO;
private static StockValue ENG;
private static StockValue ELE;
private static StockValue FCC;
private static StockValue FER;
private static StockValue GAM;
private static StockValue GAS;
private static StockValue GRF;
private static StockValue IAG;
private static StockValue IBE;
private static StockValue IBR;
// indite x
private static StockValue IDR;
private static StockValue MAP;
private static StockValue OHL;
private static StockValue POP;
private static StockValue REE;
private static StockValue REP;
private static StockValue SAB;
private static StockValue SYV;
private static StockValue SCH;
private static StockValue TRE;
//telecinc o
private static StockValue TEF;

private static HashMap StockList;


// Las meto en el hashmap...

///ESTABA CON ESTO... ASI QUE ...

private static void StartHashMap(){
	StockList.put("abengoa", ANA);
	StockList.put("abertis", ABE);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
	StockList.put("abengoa", ANA);
}


//////////////////////////////////////////////////
/////////////////////////////////////////////////////

// esta es la que te interesa
public static StockValue Bet(Order tipoOrden, double money, String Stock ) throws Exception{

	//Saco e identnfico el Stock sobre el que se apuesta
	
		//Si existe, sigo.
	
		//Si no existe, devuelvo null... 
	//hago calculos y modifico la relacion...
	
	//devuelvo el stock y lo vuelvo a meter en el hashmap

	
	
	return null;
}
}
