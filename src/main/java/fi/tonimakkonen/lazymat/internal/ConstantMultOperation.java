package fi.tonimakkonen.lazymat.internal;

import fi.tonimakkonen.lazymat.Matrix;

public class ConstantMultOperation extends Operation {

    final Matrix base;
    final double value;

    ConstantMultOperation(Matrix base, double value) {
        this.base = base;
        this.value = value;
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
        return base.get(y, x) * value;
    }

    @Override
    double actualCostGet() {
        // Note that we need one additional multiplication
        return base.costGet() + 1;
    }

    @Override
    double actualCostCalc() {
        Class<? extends ActualMatrix> cl = base.calcClass();
        if (cl == DenseMatrix.class) {
            return base.costCalc() + base.size();
        } else if (cl == DiagonalMatrix.class) {
            int size = Math.min(base.height(), base.width());
            return base.costCalc() + size;
        } else {
            throw new IllegalStateException("Unknown class: " + cl);
        }
    }

    @Override
    public Class<? extends ActualMatrix> calcClass() {
        // Multiplying by a constant does not change the base class
        return base.calcClass();
    }

    ActualMatrix actualCalc() {

        // We need the actual, raw data
        ActualMatrix b = base.calc();

        if (b instanceof DenseMatrix) {
            DenseMatrix dm = (DenseMatrix) b;
            double [] newData = new double[dm.data.length];
            for (int i = 0; i < dm.data.length; i++) {
                newData[i] = dm.data[i] * value;
            }
            return new DiagonalMatrix(dm.height, dm.width, newData);
        } else if (b instanceof  DiagonalMatrix) {
            DiagonalMatrix dm = (DiagonalMatrix) b;
            double [] newDiagonal = new double[dm.diagonal.length];
            return new DiagonalMatrix(dm.height, dm.width, newDiagonal);
        } else {
            throw new IllegalStateException("Unknown class: " + b);
        }

    }
}
