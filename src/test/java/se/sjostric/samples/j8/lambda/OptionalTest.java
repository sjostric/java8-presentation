package se.sjostric.samples.j8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class OptionalTest {

	private List<Integer> intArr;
	private List<Person> persons;

	@Before
	public void setUp() throws Exception {
		intArr = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		persons = Arrays.asList(new Person("Apan", 10), new Person("Papan", 20), new Person("Apan", 10),
				new Person("Apan", 12), new Person("Kalle", "Erik", 12));
	}

	@Test
	public void testOptional() {
		System.out.println("*** Find all persons and print their mid name, or else MISSING");
		persons.stream().map(t -> t.getMidName().orElse("MISSING")).forEach(System.out::println);

		Optional<Person> firstWithNoMidName = persons.stream().filter(t -> !t.getMidName().isPresent()).findFirst();
		firstWithNoMidName.ifPresent(t -> System.out.println(t.getMidName().orElse("NO MIDNAME")));

		try {
			firstWithNoMidName.ifPresent(
					t -> System.out.println(t.getMidName().orElseThrow(() -> new IllegalStateException(""))));
			throw new IllegalStateException("Not expected");
		} catch (Exception e) {
			System.out.println("Got the expected exception since mid name is missing");
		}
	}

}
