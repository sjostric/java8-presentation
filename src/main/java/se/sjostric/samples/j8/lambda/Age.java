package se.sjostric.samples.j8.lambda;

public interface Age {

	public int age();

	default int yearsToRetirement() {
		return 65 - age();
	}

}
