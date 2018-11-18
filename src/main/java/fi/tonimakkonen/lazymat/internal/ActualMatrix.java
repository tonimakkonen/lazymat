package fi.tonimakkonen.lazymat.internal;

/**
 * <p>
 *     Base class for any actual matrix, i.e. a matrix where no calculation is needed to access the data. All costs are
 *     zero, calls to {@link #calc()} returns {@code this}, and related methods do nothing.
 * </p>
 * <p>
 *     Implementing classed must be declared final and are responsible for storing the data. Every {@link Operation}
 *     must be able to handle every implementation of this class.
 * </p>
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
    public Class<? extends ActualMatrix> calcClass() {
        return this.getClass();
    }

    @Override
    public void need(int count) {
    }

    @Override
    public void needAll() {
    }
}
