package procurementMarketBehaviours;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

public class ReportFinancesBehaviour extends OneShotBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -6365251601845699295L;
    private String orderText;
    private OrderDataStore dataStore;
    private ProcurementMarketResponder interactionBehaviour;
    private MessageObject msgObj;

    public ReportFinancesBehaviour(ProcurementMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent());
        this.interactionBehaviour = interactionBehaviour;
        this.dataStore = dataStore;
    }

    @Override
    public void action() {
        orderText = Order.gson.fromJson(dataStore.getRequestMessage().getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject("AgentProcurementMarket", orderText +  " is in finances");
        Communication.server.sendMessageToClient(msgObj);


        myAgent.addBehaviour(new ReportFinancesInitiatorBehaviour(interactionBehaviour, dataStore));
    }
}