package se.sjostric.samples.j8.lambda;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class InterfaceDefaultMethodsTest {

	private List<Person> persons;

	@Before
	public void setUp() throws Exception {
		persons = Arrays.asList(new Person("Apan", 10), new Person("Papan", 20), new Person("Nisse", 10),
				new Person("Rickard", 12), new Person("Kalle", "Erik", 18));
	}

	@Test
	public void testAge() {
		persons.stream().findFirst().ifPresent(p -> System.out.println(p.age()));
	}

}
