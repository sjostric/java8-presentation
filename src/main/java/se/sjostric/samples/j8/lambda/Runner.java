package se.sjostric.samples.j8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Runner {

	public static void main(String[] args) {
		System.out.println("Running...");

		List<Integer> myList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		myList.forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.println("Item: " + t);
			}
		});

		myList.forEach(number -> System.out.println(number));

		myList.forEach(System.out::println);

	}

}
