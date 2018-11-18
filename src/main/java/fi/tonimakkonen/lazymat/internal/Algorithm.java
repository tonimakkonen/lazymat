package fi.tonimakkonen.lazymat.internal;


import fi.tonimakkonen.lazymat.Matrix;
import fi.tonimakkonen.lazymat.internal.TransposeOperation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Algorithm {

    static Matrix subMatrix(Matrix m, int y, int x, int heigh, int width) {
        throw new NotImplementedException();
    }

    static Matrix add(Matrix a, Matrix b) {
        throw new NotImplementedException();
    }

    static Matrix mult(Matrix m, double c) {
        // TODO: Need to check for smart optimisation
        if (m instanceof ConstantMultOperation) {
            ConstantMultOperation b = (ConstantMultOperation) m;
            return new ConstantMultOperation(b.base, b.value * c);
        }
        return new ConstantMultOperation(m, c);
    }

    static Matrix mult(Matrix a, Matrix b) {
        throw new NotImplementedException();
    }

    static Matrix transpose(Matrix m) {
        // TODO: Need to check further for smart optimisations
        if (m instanceof TransposeOperation) {
            TransposeOperation to = (TransposeOperation) m;
            return to.base;
        }
        return new TransposeOperation(m);
    }
}
