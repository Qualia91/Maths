package com.nick.wood.maths.objects.matrix;

import com.nick.wood.maths.objects.vector.Vec2f;
import com.nick.wood.maths.objects.vector.Vecf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Matrix2fTest {

    /**Getter test
     *
     */
    @Test
    void getTest() {

        Matrix2f matrix2d = Matrix2f.Identity;
        assertEquals(matrix2d.get(0, 0), 1.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 0), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(0, 1), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 1), 1.0, 0.0000000001);

    }

    @Test
    void addMatrixTest() {

        Matrix2f one = Matrix2f.Identity;
        Matrix2f two = Matrix2f.Identity;
        Matrix2f matrix2d = one.add(two);
        assertEquals(matrix2d.get(0, 0), 2.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 0), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(0, 1), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 1), 2.0, 0.0000000001);

    }

    @Test
    void addVectorTest() {

        Matrix2f one = Matrix2f.Empty;
        Vec2f two = Vec2f.X;
        Matrix2f matrix2d = one.add(two);
        assertEquals(matrix2d.get(0, 0), 1.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 0), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(0, 1), 0.0, 0.0000000001);
        assertEquals(matrix2d.get(1, 1), 0.0, 0.0000000001);
    }

    @Test
    void multiplyElementWiseTest() {
        Matrix2f one = new Matrix2f(1.0f, 2.0f, 3.0f, 4.0f);
        Matrix2f two = new Matrix2f(2.0f, 0.0f, 1.0f, 2.0f);
        Matrix2f multiply = one.elementMultiply(two);
        assertEquals(multiply.get(0, 0), 2.0, 0.0000000001);
        assertEquals(multiply.get(1, 0), 0.0, 0.0000000001);
        assertEquals(multiply.get(0, 1), 3.0, 0.0000000001);
        assertEquals(multiply.get(1, 1), 8.0, 0.0000000001);

    }

    @Test
    void multiplyTest() {
        Matrix2f one = new Matrix2f(1.0f, 2.0f, 3.0f, 4.0f);
        Matrix2f two = new Matrix2f(2.0f, 0.0f, 1.0f, 2.0f);
        Matrix2f multiply = one.multiply(two);
        assertEquals(multiply.get(0, 0), 4.0, 0.0000000001);
        assertEquals(multiply.get(1, 0), 4.0, 0.0000000001);
        assertEquals(multiply.get(0, 1), 10.0, 0.0000000001);
        assertEquals(multiply.get(1, 1), 8.0, 0.0000000001);

    }

    @Test
    void determinantTest() {
        Matrix2f one = new Matrix2f(3, 8, 4, 6);
        double det = one.det();
        assertEquals(det, -14, 0.00000001);
    }

    @Test
    void scaleTest() {
        Matrix2f one = new Matrix2f(1.0f, 2.0f, 3.0f, 4.0f);
        Matrix2f scale = one.scale(10);

        assertEquals(scale.get(0, 0), 10.0, 0.0000000001);
        assertEquals(scale.get(1, 0), 20.0, 0.0000000001);
        assertEquals(scale.get(0, 1), 30.0, 0.0000000001);
        assertEquals(scale.get(1, 1), 40.0, 0.0000000001);
    }

    @Test
    void vectorMultiplyTest() {
        Matrix2f one = new Matrix2f(1.0f, 2.0f, 3.0f, 4.0f);
        Vec2f two = new Vec2f(1.0f, 2.0f);
        Vecf vecf = one.multiply(two);

        assertEquals(vecf.get(0), 5.0f, 0.0000000001);
        assertEquals(vecf.get(1), 11.0f, 0.0000000001);
    }

    @Test
    void toMatrix2fTest() {
        Matrix2f one = new Matrix2f(1.0f, 2.0f, 3.0f, 4.0f);
        Matrix2d two = one.toMatrix2d();

        assertEquals(two.get(0, 0), 1.0, 0.0000000001);
        assertEquals(two.get(1, 0), 2.0, 0.0000000001);
        assertEquals(two.get(0, 1), 3.0, 0.0000000001);
        assertEquals(two.get(1, 1), 4.0, 0.0000000001);
    }
}