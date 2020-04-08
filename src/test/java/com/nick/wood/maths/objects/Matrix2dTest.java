package com.nick.wood.maths.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix2dTest {

    /**Getter test
     *
     */
    @Test
    void getTest() {

        Matrix2d matrix2d = Matrix2d.Identity;
        assertEquals(matrix2d.get(0, 0), 1.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 0), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(0, 1), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 1), 1.0, 0.0000000001);

    }

    @Test
    void addMatrixTest() {

        Matrix2d one = Matrix2d.Identity;
        Matrix2d two = Matrix2d.Identity;
        Matrix2d matrix2d = one.add(two);
        assertEquals(matrix2d.get(0, 0), 2.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 0), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(0, 1), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 1), 2.0, 0.0000000001);

    }

    @Test
    void addVectorTest() {

        Matrix2d one = Matrix2d.Empty;
        Vec2d two = Vec2d.X;
        Matrix2d matrix2d = one.add(two);
        assertEquals(matrix2d.get(0, 0), 1.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 0), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(0, 1), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 1), 1.0, 0.0000000001);
    }

    @Test
    void multiply() {
        Matrix2d one = new Matrix2d(1.0, 2.0, 3.0, 4.0);
        Matrix2d two = new Matrix2d(2.0, 0.0, 1.0, 2.0);
        Matrix2d multiply = one.multiply(two);
        assertEquals(multiply.get(0, 0), 4.0, 0.0000000001);
        assertEquals(multiply.get(1, 0), 4.0, 0.0000000001);
        assertEquals(multiply.get(0, 1), 10.0, 0.0000000001);
        assertEquals(multiply.get(1, 1), 8.0, 0.0000000001);

    }

    @Test
    void determinantTest() {
        Matrix2d one = new Matrix2d(3, 8, 4, 6);
        double det = one.det();
        assertEquals(det, -14, 0.00000001);
    }
}