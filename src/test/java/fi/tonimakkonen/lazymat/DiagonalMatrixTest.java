package fi.tonimakkonen.lazymat;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiagonalMatrixTest {

    @Test
    public void testBasic() {

        DiagonalMatrix dm = new DiagonalMatrix(6, 5, new double[]{1.0, 2.0, 3.0, 4.0, 5.0});

        assertEquals(6, dm.height());
        assertEquals(5, dm.width());
        assertEquals(30, dm.size());

        assertEquals(1.0, dm.get(0, 0), 1e-9);
        assertEquals(2.0, dm.get(1, 1), 1e-9);
        assertEquals(3.0, dm.get(2, 2), 1e-9);
        assertEquals(4.0, dm.get(3, 3), 1e-9);
        assertEquals(5.0, dm.get(4, 4), 1e-9);

        assertEquals(0.0, dm.get(0, 1), 1e-9);
        assertEquals(0.0, dm.get(1, 0), 1e-9);
        assertEquals(0.0, dm.get(4, 3), 1e-9);
    }
}
