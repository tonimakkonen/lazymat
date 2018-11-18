package fi.tonimakkonen.lazymat.internal;

import fi.tonimakkonen.lazymat.Matrix;

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
    double actualGet(int y, int x) {
        // bounds checked here
        return base.get(x, y);
    }

    @Override
    double actualCostGet() {
        return base.costGet();
    }

    @Override
    double actualCostCalc() {
        Class<? extends ActualMatrix> cl = base.calcClass();
        if (cl == DenseMatrix.class) {
            return base.costCalc() + base.size();
        } else if (cl == DiagonalMatrix.class) {
            return base.costCalc(); // no additional work required
        } else {
            throw new IllegalStateException("Unknown class: " + cl);
        }
    }

    @Override
    public Class<? extends ActualMatrix> calcClass() {
        // Transpose operation does not change the base matrix class
        return base.calcClass();
    }

    ActualMatrix actualCalc() {

        // We need the actual, raw data
        ActualMatrix b = base.calc();

        if (b instanceof DenseMatrix) {

            DenseMatrix dm = (DenseMatrix) b;
            double[] newData = new double[dm.data.length];
            for (int y = 0; y < dm.height; y++) {
                for (int x = 0; x < dm.width; x++) {
                    // TODO: Check this is correct. Also, could be faster.
                    newData[x * dm.height + y] = dm.data[x + y * dm.width];
                }
            }
            return new DenseMatrix(dm.width, dm.height, newData);

        } else if (b instanceof  DiagonalMatrix) {

            DiagonalMatrix dm = (DiagonalMatrix) b;
            return new DiagonalMatrix(dm.width, dm.height, dm.diagonal);

        } else {
            throw new IllegalStateException("Unknown class: " + b);
        }

    }

}
