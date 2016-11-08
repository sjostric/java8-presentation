package se.sjostric.samples.j8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FunctionalBasicsTest {

	private List<Integer> intArr;
	private List<Person> persons;

	@Before
	public void setUp() throws Exception {
		intArr = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		persons = Arrays.asList(new Person("Apan", 10), new Person("Papan", 20), new Person("Apan", 10),
				new Person("Apan", 12), new Person("Kalle", "Erik", 12));
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFilter() {
		System.out.println("*** Filet, collect and then print it (the good old way)");
		List<Integer> integers = intArr.stream().filter(t -> t % 2 == 0).collect(Collectors.toList());
		for (Integer integer : integers) {
			System.out.println("Num: " + integer);
		}

		System.out.println("*** Print the list");
		intArr.stream().filter(t -> t % 2 == 0).forEach(System.out::println);
	}

	@Test
	public void testFilterAndMap() {
		System.out.println("*** Print unique persons, requires equals to be impl");
		persons.stream().distinct().forEach(System.out::println);

		System.out.println("*** Print all Apan");
		persons.stream().filter(t -> t.getName().equals("Apan")).forEach(t -> System.out.println(t));

		System.out.println("*** Sum of ages: " + persons.stream().mapToInt(t -> t.getAge()).sum());

		System.out.println("*** Average age");
		persons.stream().mapToInt(Person::getAge).average().ifPresent(System.out::println);
	}

	@Test
	public void testReduceInParallel() {
		System.out.println("*** Paralell processing of streams");
		System.out.println("*** Sum of age");
		Integer sumAge = persons.stream().map(t -> t.getAge()).reduce(0, Integer::sum);
		System.out.println(sumAge);
		sumAge = persons.stream().filter(t -> t.getAge() > 10).map(t -> t.getAge()).reduce(0, (a, b) -> (a + b));
		System.out.println(sumAge);
		sumAge = persons.stream().mapToInt(t -> t.getAge()).sum();
		System.out.println(sumAge);

		System.out.println("*** In 10 years the sum of age will be:");
		sumAge = persons.parallelStream().map(t -> t.getAge() + 10).reduce(0, (x, y) -> x + y);
		System.out.println(sumAge);

		// TODO: separate function
		Function<Person, Integer> agePlusTen = t -> {
			return t.getAge() + 10;
		};

		sumAge = persons.stream().map(agePlusTen).reduce(0, (x, y) -> x + y);
		System.out.println(sumAge);

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
