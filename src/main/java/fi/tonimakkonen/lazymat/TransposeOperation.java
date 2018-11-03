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


    double actualGet(int y, int x) {
        // bounds checked here
        return base.get(x, y);
    }

    double actualCostGet() {
        return base.costGet();
    }

    double actualCostCalc() {
        Class<? extends ActualMatrix> cl = base.calcClass();
        if (cl == DenseMatrix.class) { // final class
            return base.costCalc() + base.size();
        } else {
            throw new IllegalStateException("Unknown class: " + cl);
        }
    }

    @Override
    public Class<? extends ActualMatrix> calcClass() {
        // Transpose operation does not change the base matrix class in any
        return base.calcClass();
    }

    ActualMatrix actualCalc() {

        // We need the actual, raw data
        ActualMatrix b = base.calc();

        if (b instanceof DenseMatrix) {
            DenseMatrix dm = (DenseMatrix) b;
            double [] newData = new double[dm.data.length];
            for (int y = 0; y < dm.height; y++) {
                for (int x = 0; x < dm.width; x++) {
                    // TODO: Check this is correct. Also, could be faster.
                    newData[x*dm.height + y] = dm.data[x + y*dm.width];
                }
            }
            return calculated;
        } else {
            throw new IllegalStateException("Unknown class: " + b);
        }

    }

}
