package fi.tonimakkonen.lazymat.internal;

/**
 * <p>
 *     An operation resulting in a matrix, such as a transpose of a matrix, the multiplication of two matrices, etc.
 *     This class is responsible for handling caching of results from actual operations. Implementing classes need to
 *     implement four methods: {@link #actualGet(int, int)}, {@link #actualCostGet()}, {@link #actualCostCalc()}, and
 *     {@link #actualCalc()}.
 * </p>
 * <p>
 *     Any implementing class needs to handle every potential implementation of {@link ActualMatrix}.
 * </p>
 */
public abstract class Operation extends MatrixDefault {

    // Potential calculated
    private int maxNeed = 0;
    protected ActualMatrix calculated = null;

    @Override
    public double get(int y, int x) {
        // TODO: add mechanism for caching and updating need
        if (calculated != null) {
            return calculated.get(y, x);
        }
        return actualGet(y, x);
    }

    @Override
    public double costGet() {
        if (calculated != null) {
            return 0.0;
        }
        return actualCostGet();
    }

    @Override
    public double costCalc() {
        if (calculated != null) {
            return 0.0;
        }
        return actualCostCalc();
    }

    @Override
    public ActualMatrix calc() {
        if (calculated == null) {
            calculated = actualCalc();
        }
        return calculated;
    }

    @Override
    public void need(int count) {
        if (calculated != null) {
            return; // no-op
        }
        if (count > size()) {
            count = size();
        }
        if (count > maxNeed) {
            maxNeed = count;
            double costWithGet = count * actualCostGet();
            double costWithCalc = actualCostCalc();
            if (costWithCalc >= costWithGet) {
                calculated = actualCalc();
            }
        }
    }

    // extending classes need to implement these
    // This base class handles caching of results

    double actualGet(int y, int x) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    double actualCostGet() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    double actualCostCalc() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    ActualMatrix actualCalc() {
        throw new UnsupportedOperationException("Method not implemented");
    }

}
