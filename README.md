# Java 8 Presentation

Java 8 presentation held by me at my company office Dec 2015. 

Pupose was:
* Practice Java 8
* Practice presenting
* Competence spread

## Contents

* Java 7 is dead
* Functional Programming
* Functional Interfaces
* Lambda Expressions
* Streams
* Optional
* Interfaces default methods
* More Java 8 changes

## Java 7 is dead

Information from Oracle:

![Alt text](images/java7-dead.png?raw=true "Java 7 is dead")

## Functional Programming
* Declarative, defines the structure and logic, not the control flow
* Output depends on input only, applying f(x) several times give the same output
* Code that are side-effect free are easier to debug
* Java 8 still OO, but brings the functional programming style

## Functional Interfaces

* Functional interfaces represents a "function"
* Exactly one abstract method
* See "abstract methods" on Javadoc Function
* @FunctionalInterface, marker interface (not required), but compiler will fail if e.g more than one abstract method etc.
* Default methods are not abstract, so any number of them goes
* Normally instantiated by Lambda expressions (see Lambdas)

### Interfaces

Functional interfaces and their (single) abstract method.
* [```Predicate<T>```](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-)
  * Boolean test(T t): e.g [Stream#filter](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-)
* [```Function<T,R>```](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html)
  * R apply(T t): e.g [Stream#map](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#map-java.util.function.Function-)
* [```Supplier<T>```](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html)
  * T get(): e.g [Optional#orEsleThrow](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#orElseThrow-java.util.function.Supplier-)
* [```Consumer<T>```](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html)
  * void accept(T t): e.g [Stream#forEach](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#forEach-java.util.function.Consumer-)
* [```Comparator<T>```](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)
  * int compare(T o1, T o): e.g [Stream#sorted](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#sorted-java.util.Comparator-) (stateful)

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

## Stream

* "A sequence of elements supporting sequential/parallel aggregate operations"
* Collections are fixed while Streams are lazily computed
* Supports aggregate operations: filter, map, reduce, find, sort, collect, etc.
* Not usable after terminal operation 
* IntStream, LongStream, DoubleStream holds specific aggregate operations
* Stream are Monads in FP: Wikipedia: Monads

```java
int sum = persons.stream().filter(p -> p.getAge() > 10).mapToInt(p -> p.getAge()).sum();
```

### Pipelining

* Pipelining (streams returning streams), e.g Stream#filter
* Stream operations are either intermediate or terminal
* Processed vertically for non-stateful operations


![Alt text](images/stream-pipe.png?raw=true "Streams")

```java
int sum = persons.stream().filter(p -> p.getAge() > 10).mapToInt(p -> p.getAge()).sum();
List<Person> ps = persons.stream().filter(p -> p.getAge() > 10).collect(Collectors.toList());
```

DEMO: [StreamLazinessTest.java](src/test/java/se/sjostric/samples/j8/lambda/StreamLazinessTest.java)

## Interfaces default methods
Test code:
[InterfaceDefaultMethodsTest.java](src/test/java/se/sjostric/samples/j8/lambda/InterfaceDefaultMethodsTest.java)

Interface:
[Age.java](src/main/java/se/sjostric/samples/j8/lambda/Age.java)

## Optionals

* Never return null again
* Not a functional thing
* Inherits Object and has several or*, is* methods
* See [Javadoc Optional](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)

### Example

```java
class Person {
  public Optional<String> getMidName() {
    return Optional.ofNullable(midName);
}
```

```java
persons.stream().map(t -> t.getMidName().orElse("MISSING")).forEach(System.out::println);
```

DEMO: [OptionalTest.java](src/test/java/se/sjostric/samples/j8/lambda/OptionalTest.java)

## More Java 8 changes

* New Date API
* Memory model
* Collections/Map
* flatMap, collectors, parallel stream etc


## Summary

* Time to move on to Java 8
* Functional programming style introduced into Java 8
* Lambdas initializes functional interfaces.
* Compact syntax with kept compile time checks
* Streams have intermediate and terminal operations.
* Streams are lazily/vertically processed
* Optional can replace null checks
* Are there any down sides with all this?


## Resources

* https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
* http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
* https://github.com/winterbe/java8-tutorial
* http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
* http://java.amitph.com/2014/01/java-8-streams-api-laziness.html


