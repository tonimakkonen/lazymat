package fi.tonimakkonen.lazymat;

public class TransposeOperation extends Operation {

    final Matrix base;

    TransposeOperation(Matrix base) {
        this.base = base;
    }

    @Override
    public int height() {
        return base.width();
    }

    @Override
    public int width() {
        return base.height();
    }

    @Override
    public int size() {
        return base.size();
    }

    @Override
    public double get(int y, int x) {
        // checking y & x done in either one
        if (calculated != null) {
            return calculated.get(y, x);
        } else {
            return base.get(x, y);
        }
    }

    @Override
    public double costGet() {
        if (calculated != null) {
            return 0.0;
        } else {
            return base.costGet();
        }
    }

    @Override
    public double costCalc() {
        if (calculated != null) {
            return 0.0;
        } else {
            // in order to calculate the new matrix, we need to calculate the original matrix and copy the data
            return base.costCalc() + size();
        }
    }

    @Override
    public MatrixBase calc() {
        // We need the actual, raw data
        MatrixBase b = base.calc();
        double [] newData = new double[b.data.length];
        for (int y = 0; y < b.height; y++) {
            for (int x = 0; x < b.width; x++) {
                // TODO: Check this is correct. Also, could be faster.
                newData[x*b.height + y] = b.data[x + y*b.width];
            }
        }
        calculated = new MatrixBase(base.width(), base.height(), newData);
        return calculated;
    }

}
