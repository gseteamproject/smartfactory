package basicAgents;

import interactors.OrderDataStore;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import procurementMarketBehaviours.ProcurementMarketResponder;

public class ProcurementMarket extends Agent {

    private static final long serialVersionUID = -7418692714860762106L;
    protected OrderDataStore dataStore;

    @Override
    protected void setup() {
        MessageTemplate reqTemp = AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST);

        dataStore = new OrderDataStore();

        // adding behaviours
        addBehaviour(new ProcurementMarketResponder(this, reqTemp, dataStore));
    }
}
