# Akka PI Tutorial
A tutorial that shows how to use Akka to calculate PI.

PI can be approximated by generating a uniformly distributed number of `x,y` coordinates between 0 and 1 and then calculating:

![equation](http://latex.codecogs.com/gif.latex?%5Cpi%20%5Capprox%204%20%7B%20%7C%5C%7B(x%2Cy)%7Cx%5E2%2By%5E2%20%3C%201%20%5C%7D%7C%20%5Cover%20N%20%7D)

# Requirements

* Java JDK 1.8 or greater (JDK 11 is fine): https://jdk.java.net/java-se-ri/11
* Sbt (Scala Build Tool): https://www.scala-sbt.org/

Uncompress the toools somewhere.

Make sure these paths are in your `PATH` environment variable:
- `/path/to/jdk/bin`
- `/path/to/sbt/bin`


# Build
Use Scala 2.12.x and SBT 1.2.x or greater.

```bash
sbt compile
```

# Run

```bash
sbt "run 100 2000"
```

# Test

```bash
sbt test
```
