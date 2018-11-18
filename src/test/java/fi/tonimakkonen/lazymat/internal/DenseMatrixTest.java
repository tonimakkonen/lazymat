package fi.tonimakkonen.lazymat.internal;

import fi.tonimakkonen.lazymat.internal.DenseMatrix;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DenseMatrixTest {

    @Test
    public void testBasic() {
        DenseMatrix denseMat = new DenseMatrix(2, 3, new double[] {0, 1, 2, 10, 11, 12});
        assertEquals(2, denseMat.height());
        assertEquals(3, denseMat.width());
        assertEquals(0.0, denseMat.get(0, 0), 1.0e-9);
        assertEquals(1.0, denseMat.get(0, 1), 1.0e-9);
        assertEquals(2.0, denseMat.get(0, 2), 1.0e-9);
        assertEquals(10.0, denseMat.get(1, 0), 1.0e-9);
        assertEquals(11.0, denseMat.get(1, 1), 1.0e-9);
        assertEquals(12.0, denseMat.get(1, 2), 1.0e-9);
    }

    @Test
    public void testGetOutOfBounds() {
        DenseMatrix denseMat = new DenseMatrix(2, 3, new double[] {0, 1, 2, 10, 11, 12});
        try {
            denseMat.get(-1, 0);
            throw new AssertionError("Expected IllegalArgumentError");
        } catch (IllegalArgumentException e) {
            // expected
        }
        try {
            denseMat.get(2, 0);
            throw new AssertionError("Expected IllegalArgumentError");
        } catch (IllegalArgumentException e) {
            // expected
        }
        try {
            denseMat.get(0, -1);
            throw new AssertionError("Expected IllegalArgumentError");
        } catch (IllegalArgumentException e) {
            // expected
        }
        try {
            denseMat.get(0, 3);
            throw new AssertionError("Expected IllegalArgumentError");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
