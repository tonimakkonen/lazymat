package fi.tonimakkonen.lazymat;

/**
 * Interface for a matrix. This can be implemented by either an {@link ActualMatrix} containing the matrix data or an
 * {@link Operation} that results in a matrix, such as the multiplication of two matrices.
 */
public interface Matrix {

    /**
     * Height of matrix, i.e. number of rows.
     * @return height
     */
    int height();

    /**
     * Width of matrix, i.e. number of columns.
     * @return width
     */
    int width();

    /**
     * Number of elements in matrix. This equals {@link #height()} times {@link #width()}.
     * @return number of elements
     */
    int size();

    /**
     * Get element at (y, x) where y is the row and x is the column. (0,0) is top left in normal notation. This method
     * has essentially no computational cost when dealing with an {@link ActualMatrix}. However, for an
     * {@link Operation} getting actual values might carry computational cost and can trigger calculations such as
     * {@link #calc()} or have values cached.
     * @param y row
     * @param x column
     * @return value at (y, x)
     * @throws IllegalArgumentException if y or x are outside the matrix
     * @see #costGet()
     * @see #costCalc()
     * @see #calc() 
     */
    double get(int y, int x);

    /**
     * Get a sub matrix of this matrix.
     * @param y starting row
     * @param x starting column
     * @param height height of the sub matrix
     * @param width width of the sub matrix
     * @return sub matrix
     * @throws IllegalArgumentException if the created matrix goes outside the original matrix
     */
    Matrix subMatrix(int y, int x, int height, int width);

    /**
     * Matrix addition
     * @param m matrix to add
     * @return new matrix
     */
    Matrix add(Matrix m);

    /**
     * Multiply this matrix by a constant.
     * @param c constant
     * @return new matrix
     */
    Matrix mult(double c);

    /**
     * Matrix multiplication from the right hand side, i.e. A * B where A is this matrix and B is passed as the
     * parameter.
     * @param m another matrix
     * @return new matrix
     */
    Matrix mult(Matrix m);

    /**
     * Transpose this matrix
     * @return transposed matrix
     */
    Matrix transpose();

    /**
     * Cost of calling {@link #get(int, int)}, i.e. calculating one value in the matrix.
     * @return cost of a get operation
     */
    double costGet();

    /**
     * Cost of calling {@link #calc()}, i.e. calculating the entire matrix.
     * @return cost of calc operation
     */
    double costCalc();

    /**
     * Calculate the entire matrix, i.e. every single element. Subsequent calls to {@code calc}, {@link #need(int)}, and
     * {@link #needAll()} will have no further effect. Also, calling {@link #get(int, int)} will return in constant
     * time.
     * @return matrix in base form, i.e. {@link DenseMatrix}
     */
    ActualMatrix calc();

    /**
     * Best estimate of returned class from the {@link #calc()} operation.
     * @return class
     */
    Class<? extends ActualMatrix> calcClass();

    /**
     * Indiate that you need to know {@code count} number of elements of this matrix. Calling this method possibly calls
     * {@link #calc()} if it cheaper to calculate the entire matrix instead of {@link #get(int, int)} {@code count}
     * number of times
     * @param count number elements that needs to be known
     */
    void need(int count);

    /**
     * Call this method to indicate that you need to know every element of this matrix. Same as calling
     * {@link #need(int)} with {@link #size()} as the parameter.
     */
    void needAll();
}
