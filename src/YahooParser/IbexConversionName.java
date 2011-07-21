package YahooParser;

public class IbexConversionName {

	
	/*
	 * We need to convers the knowed stock names under the yahoo's symbols stock
	 */

	public static String converseStocks(IbexStock stock) {

		switch (stock) { // Eleige la opcion acorde al numero de mes

		case abengoa:
			return "ABG.MC";
		case abertis:
			return "ABE.MC";
		case acciona:
			return "ANA.MC";
		case acerinox:
			return "ACX.MC";
		case acs:
			return "ACS.MC";
		case amadeus:
			return "AMS.MC";
		case arcerolmittal:
			return "MTS.MC";
		case bankinter:
			return "BKT.MC";
		case bbva:
			return "BBVA.MC";
		case bme:
			return "BME.MC";
		case caixabank:
			return "CABL.MC";
		case ebrofoods:
			return "EBRO.MC";
		case enagas:
			return "ENG.MC";
		case endesa:
			return "ELE.MC";
		case fcc:
			return "FCC.MC";
		case ferrovial:
			return "FER.MC";
		case gamesa:
			return "GAM.MC";
		case gasnatural:
			return "GAS.MC";
		case grifols:
			return "GRF.MC";
		case iag:
			return "IAG.MC";
		case iberdrola:
			return "IBE.MC";
		case iberdrolarenovables:
			return "IBR.MC";
		case inditex:
			return "";
		case indra:
			return "IDR.MC";
		case mapfre:
			return "MAP.MC";
		case ohl:
			return "OHL.MC";
		case popular:
			return "POP.MC";
		case ree:
			return "REE.MC";
		case repsol:
			return "REP.MC";
		case repsolypf:
			return "REP.MC";
		case sabadell:
			return "SAB.MC";
		case sacyr:
			return "SYV.MC";
		case santander:
			return "SCH.MC";
		case bancosantander:
			return "SCH.MC";
		case bsch:
			return "SCH.MC";
		case tecnicasreunidas:
			return "TRE.MC";
		case telecinco:
			return "";
		case telefonica:
			return "TEF.MC";
		}
		return null;
	}

}
