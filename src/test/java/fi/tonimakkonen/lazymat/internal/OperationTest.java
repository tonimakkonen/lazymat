package fi.tonimakkonen.lazymat.internal;

import fi.tonimakkonen.lazymat.internal.ActualMatrix;
import fi.tonimakkonen.lazymat.internal.Operation;

public class OperationTest {

    static class DummyOperation extends Operation {

        int actualGetCalled = 0;

        @Override
        public int height() {
            return 4;
        }

        @Override
        public int width() {
            return 3;
        }

        @Override
        public int size() {
            return 12;
        }

        @Override
        public Class<? extends ActualMatrix> calcClass() {
            return null; // not used by Operation
        }

        double actualGet(int y, int x) {
            return 1.0;
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
}
