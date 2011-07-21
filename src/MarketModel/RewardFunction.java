package MarketModel;

public class RewardFunction {

	//changed
	
	// Constants for the polinomical graph (1.02<x<3.35)
	private static final double x15= -0.0000013679223;
	private static final double x14= 0.00059420743;
	private static final double x13= -0.011104575;
	private static final double x12= 0.11459601;
	private static final double x11= -0.68466255;
	private static final double x10= 2.138514;
	private static final double x9= -1.8586641;
	private static final double x8= +3.5799992;
	private static final double x7= -157.31626;
	private static final double x6= 1229.4766;
	private static final double x5= -4917.0063;
	private static final double x4= +12081.607;
	private static final double x3= -19017.431;
	private static final double x2= +18764.014;
	private static final double x1= -10590.979;
	private static final double x0= +2615.30;
	
	// Constants for the lineal graph (3.35<x<6.5)
	private static final double linx = -0.06349206;
	private static final double cons = 1.492698401;
	private static final double rest = 1.0203174746;
	
	/*
	 * It returns the pair of the other reward given one of them
	 */
	public static double rewardFunction (double a){
		
		if(a <= 1.02){
			return 10;
		}
		
		else if (a <= 3.35){
			return x15*a+x14*a+x13*a+x12*a+x11*a+x10*a+x9*a+x8*a+x7*a+x6*a+x5*a+x4*a+x3*a+x2*a+x1*a+x0;
		}
		
		//6.5 realmente
		else if(a<=7.44){
			return linx*a + cons;
		}
		else 
			return rest;
	}
	
	
	
}
