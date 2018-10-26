package fi.tonimakkonen.lazymat;

public class MatrixBase extends MatrixDefault {

    final int height, width;
    final double [] data;

    // Package private constructor. No validity checks and no copying of data.
    MatrixBase(int height, int width, double [] data) {
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

    @Override
    public double costGet() {
        // TODO: should this be two? (one mult and one sum)
        return 0.0;
    }

    @Override
    public double costCalc() {
        return 0.0; // no-op
    }

    @Override
    public MatrixBase calc() {
        return this; // no-op
    }

    @Override
    public void need(int count) {
        // no-op
    }
}
