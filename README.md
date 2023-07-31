## Compact Genetic Algorithm in Scala

### Usage

Suppose that the objective function takes binary input and returns the sum of the values as

```scala
def f(x: List[Int]): Double = x.sum * 1.0
```

and it is clear that any List with values (0, 0, ..., 0) minimized f. The scala implementation 
of Compact Genetic Algorithms can be used to minimize this function using 

```scala
val result = GA.cga(f, 10, 0.001)
```

where f is the objective function defined above, 10 is the bit length, 0.001 is the amount 
of mutation in each single iteration. The result is a List[Int] object with size 10:

```
List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
```


The user is asked to provide these three information as input. 

