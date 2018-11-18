package fi.tonimakkonen.lazymat.internal;

/**
 * Dense matrix
 */
public final class DenseMatrix extends ActualMatrix {

    final int height, width;
    final double [] data;

    // Package private constructor. No validity checks and no copying of data.
    DenseMatrix(int height, int width, double [] data) {
        this.height = height;
        this.width = width;
        this.data = data;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public double get(int y, int x) {
        checkInside(y, x);
        return data[x + y*width];
    }

}
