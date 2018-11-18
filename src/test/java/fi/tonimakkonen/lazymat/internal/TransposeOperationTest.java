package fi.tonimakkonen.lazymat.internal;

import fi.tonimakkonen.lazymat.Matrix;
import fi.tonimakkonen.lazymat.internal.DenseMatrix;
import fi.tonimakkonen.lazymat.internal.TransposeOperation;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TransposeOperationTest {

    @Test
    public void testDimensions() {
        Matrix base = mock(Matrix.class);
        when(base.height()).thenReturn(5);
        when(base.width()).thenReturn(10);
        when(base.size()).thenReturn(50);
        TransposeOperation to = new TransposeOperation(base);
        assertEquals(10, to.height());
        assertEquals(5, to.width());
        assertEquals(50, to.size());
    }

    @Test
    public void testActualGet() {
        Matrix base = mock(Matrix.class);
        when(base.get(5, 10)).thenReturn(10.0);
        TransposeOperation to = new TransposeOperation(base);
        double result = to.actualGet(10, 5);
        assertEquals(10.0, result, 1.0e-9);
        verify(base, times(1)).get(5, 10);
    }

    @Test
    public void testActualCostGet() {
        Matrix base = mock(Matrix.class);
        when(base.costGet()).thenReturn(7.0);
        TransposeOperation to = new TransposeOperation(base);
        assertEquals(7.0, to.actualCostGet(), 1e-9);
    }

    @Test
    public void testCostDense() {
        Matrix base = mock(Matrix.class);
        when(base.costGet()).thenReturn(1.0);
        when(base.costCalc()).thenReturn(10.0);
        doReturn(DenseMatrix.class).when(base).calcClass(); // note, mockito needs this
        when(base.size()).thenReturn(20);

        TransposeOperation to = new TransposeOperation(base);
        assertEquals(1.0, to.actualCostGet(), 1e-9);
        // cost of actual get
        assertEquals(10.0 + 20.0, to.actualCostCalc(), 1e-9);
    }
}
