package se.sjostric.samples.j8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class StreamLazinessTest {

	private List<Person> persons;

	@Before
	public void setUp() throws Exception {
		persons = Arrays.asList(new Person("Apan", 10), new Person("Papan", 20), new Person("Nisse", 10),
				new Person("Rickard", 12), new Person("Kalle", "Erik", 18), new Person("Nicke", 13));
	}

	@Test
	public void testLazinessDifferentStream() {
		Stream<Person> adults = persons.stream().filter(p -> {
			System.out.println("Checking: " + p);
			return p.getAge() >= 18;
		});
		boolean checkAdults = true;
		if (checkAdults) {
			// terminal operation called, filter will be processed
			List<Person> adultList = adults.collect(Collectors.toList());
			adultList.stream().forEach(p -> System.out.println("forEach: " + p));
		}
		System.out.println("DONE");
	}

	@Test
	public void testLazinessSameStream() {
		// note the vertically processing between the stream operations
		persons.stream().filter(p -> {
			System.out.println("Checking: " + p);
			return p.getAge() >= 18;
		}).forEach(p -> System.out.println("forEach: " + p));
	}

	@Test
	public void testLazinessSameStreamFindAny() {
		// processing of the list is only done until the findAny is satisfied,
		// together with the vertical nature of the processing this reduces work
		// being done
		persons.stream().filter(p -> {
			System.out.println("Checking: " + p);
			return p.getAge() >= 18;
		}).findAny().ifPresent(System.out::println);
	}

}
