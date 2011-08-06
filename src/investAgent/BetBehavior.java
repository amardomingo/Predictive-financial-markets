package investAgent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SenderBehaviour;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.lang.acl.*;

/**
 * Creates and send the bets to the market.
 * 
 * @author Alberto Mardomingo
 * @version 20110802 0.1 
 */
public class BetBehavior extends CyclicBehaviour {
	
	private InvestAgent invester;
	
	public BetBehavior(InvestAgent invester){
		this.invester = invester;
	}
	public void action(){
		try {
			File file = new File("../../bets.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine())!= null){
				/*
				 * I'm supossed to have the correct syntaxes in the file
				 * NombreAccion/TipoOrden/DineroInvertido/SentidoOrden/DiasDeInversión/CondicionesEspecialesParaOrdenesLimitadas
				 * 
				 * I ignore lines starting with #
				 */
				if (!(line.startsWith("#"))){
					ACLMessage message = new ACLMessage(ACLMessage.INFORM);
					AID receiver = new AID("betMarket", AID.ISLOCALNAME);
					// TODO You should probably check this...
					message.addReceiver(receiver);
					message.setContent(line);
					this.invester.send(message);
				}
			}
		} catch (Exception e) {
			System.out.println("Problem while placing a bet with the Invester Agent");
			System.out.println(e.getMessage());
		}
		block(1000000);
	}
}
