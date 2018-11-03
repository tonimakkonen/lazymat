package fi.tonimakkonen.lazymat;

public final class DiagonalMatrix extends ActualMatrix {

    int height, width;
    double [] diagonal;

    DiagonalMatrix(int height, int width, double [] diagonal) {
        // package private constructor, no checks and no copying of data
        this.height = height;
        this.width = width;
        this.diagonal = diagonal;
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
        return height * width;
    }

    @Override
    public double get(int y, int x) {
        checkInside(y, x);
        if (y != x) {
            return 0.0;
        } else {
            return diagonal[y];
        }
    }
}
