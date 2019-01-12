package smartfactory.matchers;

import jade.lang.acl.ACLMessage;

public class ACLMessageMatcher extends Matcher<ACLMessage> {

	public ACLMessageMatcher expectPerformative(int performative) {
		addExpectation(new MatcherExpectation() {
			@Override
			public void trigger(ACLMessage arg) throws MatcherException {
				compare("performative", performative, arg.getPerformative());
			}
		});
		return this;
	}

	public ACLMessageMatcher expectContent(String content) {
		addExpectation(new MatcherExpectation() {
			@Override
			public void trigger(ACLMessage arg) throws MatcherException {
				compare("content", content, arg.getContent());
			}
		});
		return this;
	}

	public ACLMessageMatcher expectLanguage(String language) {
		addExpectation(new MatcherExpectation() {
			@Override
			public void trigger(ACLMessage arg) throws MatcherException {
				compare("language", language, arg.getLanguage());
			}
		});
		return this;
	}

	public ACLMessageMatcher expectOntology(String ontology) {
		addExpectation(new MatcherExpectation() {
			@Override
			public void trigger(ACLMessage arg) throws MatcherException {
				compare("ontology", ontology, arg.getOntology());
			}
		});
		return this;
	}
}
