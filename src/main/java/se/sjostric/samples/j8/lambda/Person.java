package se.sjostric.samples.j8.lambda;

import java.util.Optional;

/**
 * A class representing a Person
 */
class Person implements Age {
	private String name;
	private Integer age;
	private String midName;

	public Person(String name, Integer age) {
		this(name, null, age);
	}

	public Person(String name, String midName, Integer age) {
		this.name = name;
		this.midName = midName;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public Optional<String> getMidName() {
		return Optional.ofNullable(midName);
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public Integer getAge() {
		return age;
	}
	
	@Override
	public int age() {
		return getAge();
	}


	@Override
	public String toString() {
		return name + ":" + age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


}
