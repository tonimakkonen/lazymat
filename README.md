# LazyMat

The idea of *Lazy Matrix* is to be able to write simple matrix operations in Java without having to think about optimisation. The libabry reduces the number of operations in the background for you. This is achieved in two ways: lazy evaluation and automatic matrix type optimisation.

## Lazy evaluation

Lazy evaluation means that all operations on matrices are only calculated when needed. Writing a matrix operation in Java operation in Java does not perform the actual calculation bur rather creates an internal data structure indicating the operation. Such as "this matrix multiplied by that matrix", or "the transpose of this matrix", or "a sub matrix of this matrix".

```java
Matrix m1, m2, m3;
// m1 = some large matrix (n by n)
// m2 = another large matrix (n by n)
m3 = m1.mult(m2);
m3 = m3.mult(5.0);
System.out.println(m3.get(1, 1)); // something has to be calculated
```

When `m3.get(1, 1)` is called, the libary will likely calculate this as inner product between row 1 and column 1 of matrices m1 and m2, and multiply the value by 5. There is no need to calculate the matrix product of two n by n matrices and then multiply every single value by 5 just to get one of those values. Of course, this depends in the size and type of the matrices in question and whether more values of `m3` are needed.

## Matrix type optimisation

Lazymat reduces the number of operations by understanding different matrix classes. Consioder the following code.

```java
MatrixBuilder mb = MatrixBuilder.get();
Matrix m1 = mb.create(4, 4, 1.0, 0.0, 0.0, 0.0, 0.0, 2.0, 0.0, 0.0, 0.0, 0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 4.0);
Matrix m2 = mb.create(4, 4, 1.0, 1.0, 0.0, 1.0, 0.0, 2.0, 3.0, 5.0, 5.0, 0.0, 3.0, 7.0, 1.0, 8.0, 1.0, 3.0);
Matrix m3 = m1.mult(m2);
```

Lazymat understand that `m1` is a diagonal matrix and is able to use that information to speed up e.g. multiplications.
