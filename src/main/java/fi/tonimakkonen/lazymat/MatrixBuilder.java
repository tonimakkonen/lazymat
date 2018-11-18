package fi.tonimakkonen.lazymat;

import fi.tonimakkonen.lazymat.internal.InternalMatrixBuilder;

/**
 * Used to build matrices
 */
public class MatrixBuilder extends InternalMatrixBuilder {

    private static volatile MatrixBuilder instance = null;
    private static final Object lock = new Object();

    public static MatrixBuilder get() {
        MatrixBuilder r = instance;
        if (r == null) {
            synchronized (lock) {
                r = instance;
                if (r == null) {
                    r = new MatrixBuilder();
                    instance = r;
                }
            }
        }
        return r;
    }

    /**
     * Create a new
     * @param height number of rows
     * @param width number of columns
     * @param data matrix entries in row-wise order
     * @return new Matrix
     */
    public Matrix create(int height, int width, double ... data) {
        if (height < 1) {
            throw new IllegalArgumentException("height must be postive: " + height);
        }
        if (width < 1) {
            throw new IllegalArgumentException("width must be positive: " + width);
        }
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        int size = height * width;
        if (data.length != size) {
            throw new IllegalArgumentException("expected data length " + size + " but is " + data.length);
        }
        // TODO: check what matrix type is the smartest
        return newDense(height, width, data);
    }
    
}
