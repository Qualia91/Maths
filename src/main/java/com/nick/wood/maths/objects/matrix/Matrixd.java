package com.nick.wood.maths.objects.matrix;

import com.nick.wood.maths.objects.vector.Vecd;

public interface Matrixd {

    Matrixd add(Matrixd matrix);
    Matrixd add(Vecd vecd);
    Matrixd multiply(Matrixd matrix);
    Matrixd multiply(Vecd vecd);
    Matrixd scale(double s);
    double get(int x, int y);



}
