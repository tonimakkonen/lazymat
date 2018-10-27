package fi.tonimakkonen.lazymat;

/**
 * Base class for any matrix that has the element data stored and does not need to be calculated, e.g.
 * {@link DenseMatrix}
 */
public abstract class ActualMatrix extends MatrixDefault {

    // Actual matrices are already calculated

    @Override
    public double costGet() {
        return 0.0;
    }

    @Override
    public double costCalc() {
        return 0.0;
    }

    @Override
    public ActualMatrix calc() {
        return this;
    }

    @Override
    public void need(int count) {
    }

    @Override
    public void needAll() {
    }
}
