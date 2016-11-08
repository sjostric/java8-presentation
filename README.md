# Java 8 Presentation

Java 8 presentation held by me at my company office Dec 2015. 

## Contents

* Java 7 is dead
* Functional Programming
* Functional Interfaces
* Lambda Expressions
* Streams
* Optional
* More Java 8 changes

## Java 7 is dead

![Alt text](images/java7-dead.png?raw=true "Java 7 is dead")

## Functional Programming
* Declarative, defines the structure and logic, not the control flow
* Output depends on input only, applying f(x) several times give the same output
* Code that are side-effect free are easier to debug
* Java 8 still OO, but brings the functional programming style

## DEMO: default methods
Test code:
[InterfaceDefaultMethodsTest.java](src/test/java/se/sjostric/samples/j8/lambda/InterfaceDefaultMethodsTest.java)

Interface:
[Age.java](src/main/java/se/sjostric/samples/j8/lambda/Age.java)


## Functional Interfaces

* Functional interfaces represents a "function"
* Exactly one abstract method
* See "abstract methods" on Javadoc Function
* @FunctionalInterface, marker interface (not required), but compiler will fail if e.g more than one abstract method etc.
* Default methods are not abstract, so any number of them goes
* Normally instantiated by Lambda expressions (see Lambdas)

### Interfaces

Functional interfaces and their (single) abstract method.
* Predicate<T>
  * Boolean test(T t): e.g Stream#filter
* Function<T,R>
  * R apply(T t): e.g Stream#map
* Supplier<T>
  * T get(): e.g Optional#orEsleThrow
* Consumer<T>
  * void accept(T t): e.g Stream#forEach
* Comparator<T>
  * int compare(T o1, T o): e.g Stream#sorted (stateful)

## Lambda Expressions
* (arg) -> body, e.g: (int a, int b) -> {return a +b}
* Instantiates “Functions”
  * Predicate<Person> personFilter = {p -> p.age > 10};
  * persons.stream().filter(personFilter);
  * persons.stream().filter(p -> p.age > 10);

### Lambdas: example
```java
int sumAge = 0;
for (Person p : persons){
 if (p.getAge() > 10){
    sumAge += p.getAge();
  }
}

```

```java
sumAge = persons.stream().filter(t -> t.getAge() > 10).mapToInt(t -> t.getAge()).sum();
sumAge = persons.stream().filter(t -> t.getAge() > 10).map(t -> t.getAge()).reduce(0, Integer::sum);
sumAge = persons.stream().filter(t -> t.getAge() > 10).map(t -> t.getAge()).reduce(0, (a, b) -> (a + b));
```


DEMO: [FunctionalInterfacesTest.java](/src/test/java/se/sjostric/samples/j8/lambda/FunctionalInterfacesTest.java)


