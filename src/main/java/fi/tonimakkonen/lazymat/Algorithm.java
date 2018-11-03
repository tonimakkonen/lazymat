package fi.tonimakkonen.lazymat;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Algorithm {

    static Matrix subMatrix(Matrix m, int y, int x, int heigh, int width) {
        throw new NotImplementedException();
    }

    static Matrix add(Matrix a, Matrix b) {
        throw new NotImplementedException();
    }

    static Matrix mult(Matrix m, double c) {
        throw new NotImplementedException();
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
