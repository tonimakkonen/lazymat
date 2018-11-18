package fi.tonimakkonen.lazymat.internal;

/**
 */
public class InternalMatrixBuilder {

    protected DenseMatrix newDense(int height, int width, double [] data) {
        // no check, assume calling class does this
        return new DenseMatrix(height, width, data);
    }

    protected DiagonalMatrix newDiagonal(int height, int widht, double [] data) {
        // no check, assume calling class does this
        return new DiagonalMatrix(height, widht, data);
    }
}
