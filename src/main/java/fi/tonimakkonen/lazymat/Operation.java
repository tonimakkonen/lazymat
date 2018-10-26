package fi.tonimakkonen.lazymat;

/**
 * An operation resulting in a matrix, such as a transpose of a matrix, the multiplication of two matrices, etc.
 */
public abstract class Operation extends MatrixDefault {

    MatrixBase calculated = null;
    int maxNeed = 0;

    void updateNeed(int count) {
        if (count > maxNeed) {
            maxNeed = count;
            if (costGet() * count >= costCalc()) {
                calculated = calc();
            }
        }
    }

    @Override
    public void need(int count) {
        updateNeed(count);
    }
}
