package smartfactory.matchers;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.util.leap.Iterator;

public class DFAgentDescriptionMatcher extends Matcher<DFAgentDescription> {

	public DFAgentDescriptionMatcher expectServices(ServiceDescription[] serviceDescriptions) {
		expectations.add(new MatcherExpectation() {
			@Override
			public void trigger(DFAgentDescription arg) throws MatcherException {
				Iterator iterator = arg.getAllServices();
				int i = 0;
				while (iterator.hasNext()) {
					compare("service", serviceDescriptions[i], (ServiceDescription) iterator.next());
					i++;
				}
			}
		});
		return this;
	}
}
