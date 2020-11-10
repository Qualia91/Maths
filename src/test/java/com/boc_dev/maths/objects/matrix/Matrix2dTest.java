package com.boc_dev.maths.objects.matrix;

import com.boc_dev.maths.objects.vector.Vec2d;
import com.boc_dev.maths.objects.vector.Vecd;
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
        assertEquals(matrix2d.get(1, 1), 0.0, 0.0000000001);
    }

    @Test
    void multiplyElementWiseTest() {
        Matrix2d one = new Matrix2d(1.0, 2.0, 3.0, 4.0);
        Matrix2d two = new Matrix2d(2.0, 0.0, 1.0, 2.0);
        Matrix2d multiply = one.elementMultiply(two);
        assertEquals(multiply.get(0, 0), 2.0, 0.0000000001);
        assertEquals(multiply.get(1, 0), 0.0, 0.0000000001);
        assertEquals(multiply.get(0, 1), 3.0, 0.0000000001);
        assertEquals(multiply.get(1, 1), 8.0, 0.0000000001);

    }

    @Test
    void multiplyTest() {
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

    @Test
    void scaleTest() {
        Matrix2d one = new Matrix2d(1.0, 2.0, 3.0, 4.0);
        Matrix2d scale = one.scale(10);

        assertEquals(scale.get(0, 0), 10.0, 0.0000000001);
        assertEquals(scale.get(1, 0), 20.0, 0.0000000001);
        assertEquals(scale.get(0, 1), 30.0, 0.0000000001);
        assertEquals(scale.get(1, 1), 40.0, 0.0000000001);
    }

    @Test
    void vectorMultiplyTest() {
        Matrix2d one = new Matrix2d(1.0, 2.0, 3.0, 4.0);
        Vec2d two = new Vec2d(1.0, 2.0);
        Vecd vecd = one.multiply(two);

        assertEquals(vecd.get(0), 5.0, 0.0000000001);
        assertEquals(vecd.get(1), 11.0, 0.0000000001);
    }

    @Test
    void toMatrix2fTest() {
        Matrix2d one = new Matrix2d(1.0, 2.0, 3.0, 4.0);
        Matrix2f two = one.toMatrix2f();

        assertEquals(two.get(0, 0), 1.0f, 0.0000000001);
        assertEquals(two.get(1, 0), 2.0f, 0.0000000001);
        assertEquals(two.get(0, 1), 3.0f, 0.0000000001);
        assertEquals(two.get(1, 1), 4.0f, 0.0000000001);
    }

}