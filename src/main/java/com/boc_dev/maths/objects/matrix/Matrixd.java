package com.boc_dev.maths.objects.matrix;

import com.boc_dev.maths.objects.vector.Vecd;

public interface Matrixd {

    Matrixd add(Matrixd matrix);
    Matrixd add(Vecd vecd);
    Matrixd multiply(Matrixd matrix);
    Matrixd multiply(Vecd vecd);
    Matrixd scale(double s);
    double get(int x, int y);



}
