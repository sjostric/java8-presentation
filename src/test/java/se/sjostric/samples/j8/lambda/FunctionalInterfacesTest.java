package se.sjostric.samples.j8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class FunctionalInterfacesTest {

	private List<Integer> intArr;
	private List<Person> persons;

	@Before
	public void setUp() throws Exception {
		intArr = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		persons = Arrays.asList(new Person("Apan", 10), new Person("Papan", 20), new Person("Apan", 10),
				new Person("Apan", 12), new Person("Kalle", "Erik", 12));
	}

	@Test
	public void testSqrt() {
		int input = 10;
		// create a "Function" from a lambda expression.
		Function<Integer, Integer> func = x -> x * x;

		// old way to do it
		Function<Integer, Integer> funcOldWay = new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer t) {
				return t * t;
			}
		};

		// apply the function to an argument
		System.out.println("sqrt(" + input + ") = " + func.apply(input));
		System.out.println("sqrt(" + input + ") = " + funcOldWay.apply(input));
	}

	private Stream<Integer> ageOfPersons(Stream<Person> ps, Function<Person, Integer> ageOf) {
		return persons.stream().map(ageOf);
	}

	@Test
	public void testCalculateAgesInTenYears() {
		// Function<T,R>: T input, R is the return type
		// this creates an instance of the functional interfaces using a Lambda
		// expression
		Function<Person, Integer> agePlusTen = t -> {
			return t.getAge() + 10;
		};
		Stream<Integer> ageStream = ageOfPersons(persons.stream(), agePlusTen);

		// abstract method inherited from BiFunction: apply(T t, U u)
		// x and y are being "applied"
		BinaryOperator<Integer> sumAges = (x, y) -> x + y;
		Integer sumAge = ageStream.reduce(0, sumAges);
		System.out.println(sumAge);

	}

}
