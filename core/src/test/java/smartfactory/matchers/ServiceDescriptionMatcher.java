package smartfactory.matchers;

import jade.domain.FIPAAgentManagement.ServiceDescription;

public class ServiceDescriptionMatcher extends Matcher<ServiceDescription> {

	public ServiceDescriptionMatcher expectName(String name) {
		addExpectation(new MatcherExpectation() {
			@Override
			public void trigger(ServiceDescription arg) throws MatcherException {
				compare("name", name, arg.getName());
			}
		});
		return this;
	}
}
