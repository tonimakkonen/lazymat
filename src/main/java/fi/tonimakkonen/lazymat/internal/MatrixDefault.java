package fi.tonimakkonen.lazymat.internal;

import fi.tonimakkonen.lazymat.Matrix;

/**
 * Class with most of default matrix operations
 */
public abstract class MatrixDefault implements Matrix {

    @Override
    public Matrix subMatrix(int y, int x, int height, int width) {
        return Algorithm.subMatrix(this, y, x, height, width);
    }

    @Override
    public Matrix add(Matrix m) {
        return Algorithm.add(this, m);
    }

    @Override
    public Matrix mult(double c) {
        return Algorithm.mult(this, c);
    }

    @Override
    public Matrix mult(Matrix m) {
        return Algorithm.mult(this, m);
    }

    @Override
    public Matrix transpose() {
        return Algorithm.transpose(this);
    }

    @Override
    public void needAll() {
        need(size());
    }

    void checkInside(int y, int x) {
        int h = height();
        int w = width();
        if (y < 0 || y >= h || x < 0 || x >= w) {
            throw new IllegalArgumentException("point (" + y + ", " + x + ") not inside matrix with size (" + h + ", " + w + ")");
        }
    }

}
