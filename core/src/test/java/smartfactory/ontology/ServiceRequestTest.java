package smartfactory.ontology;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.PrimitiveSchema;

public class ServiceRequestTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ServiceRequest testable;

	@Before
	public void setUp() {
		testable = new ServiceRequest();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getSchemaName() {
		Assert.assertEquals("ServiceRequest", ServiceRequest.getSchemaName());
	}

	@Test
	public void registerSchemaInOntology() throws OntologyException, NoSuchFieldException, SecurityException {
		Ontology ontology_mock = context.mock(Ontology.class);
		PrimitiveSchema schema_mock = context.mock(PrimitiveSchema.class);

		context.checking(new Expectations() {
			{
				oneOf(ontology_mock).getSchema(BasicOntology.STRING);
				will(returnValue(schema_mock));

				oneOf(ontology_mock).add(with(any(AgentActionSchema.class)), with(ServiceRequest.class));
			}
		});

		ServiceRequest.registerSchemaInOntology(ontology_mock);
	}
}
