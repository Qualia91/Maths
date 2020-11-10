package com.boc_dev.maths.objects.vector;

import com.boc_dev.maths.objects.matrix.Matrix2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vec2dTest {

    private final Vec2d one = new Vec2d(1.0, 2.0);
    private final Vec2d two = new Vec2d(3.0, 4.0);

    @Test
    void getXTest() {
        assertEquals(one.getX(), 1.0, 0.000000001);
    }

    @Test
    void getYTest() {
        assertEquals(one.getY(), 2.0, 0.000000001);
    }

    @Test
    void addTest() {
        Vec2d answer = one.add(one);

        assertEquals(answer.getX(), 2.0, 0.000000001);
        assertEquals(answer.getY(), 4.0, 0.000000001);
    }

    @Test
    void subtractTest() {
        Vec2d answer = one.subtract(one);

        assertEquals(answer.getX(), 0.0, 0.000000001);
        assertEquals(answer.getY(), 0.0, 0.000000001);
    }

    @Test
    void scaleTest() {
        Vec2d answer = one.scale(10.0);

        assertEquals(answer.getX(), 10.0, 0.000000001);
        assertEquals(answer.getY(), 20.0, 0.000000001);
    }

    @Test
    void dotTest() {
        double answer = one.dot(one);

        assertEquals(answer, 5.0, 0.000000001);
    }

    @Test
    void length2Test() {
        double answer = one.length2();

        assertEquals(answer, 5.0, 0.000000001);
    }

    @Test
    void lengthTest() {
        double answer = one.length();

        assertEquals(answer, Math.sqrt(5.0), 0.000000001);
    }

    @Test
    void normaliseTest() {
        Vec2d answer = one.normalise();

        assertEquals(answer.getX(), 1.0/Math.sqrt(5.0), 0.000000001);
        assertEquals(answer.getY(), 2.0/Math.sqrt(5.0), 0.000000001);

        // test length being 0
        Vec2d answer2 = Vec2d.ZERO.normalise();

        assertEquals(answer2.getX(), 0.0, 0.000000001);
        assertEquals(answer2.getY(), 0.0, 0.000000001);
    }

    @Test
    void getValuesTest() {
        double[] answer = one.getValues();

        assertEquals(answer.length, 2);
        assertEquals(answer[0], 1.0, 0.000000001);
        assertEquals(answer[1], 2.0, 0.000000001);
    }

    @Test
    void outerProductTest() {
        Matrix2d matrix2d = one.outerProduct(one);

        assertEquals(matrix2d.get(0, 0), 1.0, 0.000000001);
        assertEquals(matrix2d.get(1, 0), 2.0, 0.000000001);
        assertEquals(matrix2d.get(0, 1), 2.0, 0.000000001);
        assertEquals(matrix2d.get(1, 1), 4.0, 0.000000001);
    }

    @Test
    void negTest() {
        Vec2d answer = one.neg();

        assertEquals(answer.getX(), -1.0, 0.000000001);
        assertEquals(answer.getY(), -2.0, 0.000000001);
    }

    @Test
    void multiplyTest() {
        Vec2d answer = one.multiply(one);

        assertEquals(answer.getX(), 1.0, 0.000000001);
        assertEquals(answer.getY(), 4.0, 0.000000001);
    }

    @Test
    void crossTest() {
        Vec2d answer = one.cross(one);

        assertEquals(answer.getX(), 2.0, 0.000000001);
        assertEquals(answer.getY(), 2.0, 0.000000001);
    }

    @Test
    void midpoint() {
        Vec2d answer = one.midpoint(two);

        assertEquals(answer.getX(), 2.0, 0.000000001);
        assertEquals(answer.getY(), 3.0, 0.000000001);
    }

    @Test
    void getTest() {
        assertEquals(one.get(0), 1.0, 0.000000001);
        assertEquals(one.get(1), 2.0, 0.000000001);
    }

    @Test
    void toVecfTest() {
        Vec2f answer = one.toVecf();

        assertEquals(answer.getX(), 1.0, 0.000000001);
        assertEquals(answer.getY(), 2.0, 0.000000001);
    }

    @Test
    void equalsTest() {
        Vec2d a = new Vec2d(1.0, 2.0);
        Vec2d b = new Vec2d(1.0, 2.0);

        assertEquals(a, b);
    }
}