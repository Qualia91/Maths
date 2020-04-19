package com.nick.wood.maths.objects.vector;

import com.nick.wood.maths.objects.matrix.Matrix2d;
import com.nick.wood.maths.objects.matrix.Matrix4d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vec2dTest {

    private final Vec2d one = new Vec2d(1.0, 2.0);
    private final Vec2d two = new Vec2d(3.0, 4.0);

    @Test
    void getX() {
        assertEquals(one.getX(), 1.0, 0.000000001);
    }

    @Test
    void getY() {
        assertEquals(one.getY(), 2.0, 0.000000001);
    }

    @Test
    void add() {
        Vec2d answer = one.add(one);

        assertEquals(answer.getX(), 2.0, 0.000000001);
        assertEquals(answer.getY(), 4.0, 0.000000001);
    }

    @Test
    void subtract() {
        Vec2d answer = one.subtract(one);

        assertEquals(answer.getX(), 0.0, 0.000000001);
        assertEquals(answer.getY(), 0.0, 0.000000001);
    }

    @Test
    void scale() {
        Vec2d answer = one.scale(10.0);

        assertEquals(answer.getX(), 10.0, 0.000000001);
        assertEquals(answer.getY(), 20.0, 0.000000001);
    }

    @Test
    void dot() {
        double answer = one.dot(one);

        assertEquals(answer, 5.0, 0.000000001);
    }

    @Test
    void length2() {
        double answer = one.length2();

        assertEquals(answer, 5.0, 0.000000001);
    }

    @Test
    void length() {
        double answer = one.length();

        assertEquals(answer, Math.sqrt(5.0), 0.000000001);
    }

    @Test
    void normalise() {
        Vec2d answer = one.normalise();

        assertEquals(answer.getX(), 1.0/Math.sqrt(5.0), 0.000000001);
        assertEquals(answer.getY(), 2.0/Math.sqrt(5.0), 0.000000001);
    }

    @Test
    void getValues() {
        double[] answer = one.getValues();

        assertEquals(answer.length, 2);
        assertEquals(answer[0], 1.0, 0.000000001);
        assertEquals(answer[1], 2.0, 0.000000001);
    }

    @Test
    void outerProduct() {
        Matrix2d matrix2d = one.outerProduct(one);

        assertEquals(matrix2d.get(0, 0), 1.0, 0.000000001);
        assertEquals(matrix2d.get(1, 0), 2.0, 0.000000001);
        assertEquals(matrix2d.get(0, 1), 2.0, 0.000000001);
        assertEquals(matrix2d.get(1, 1), 4.0, 0.000000001);
    }

    @Test
    void neg() {
        Vec2d answer = one.neg();

        assertEquals(answer.getX(), -1.0, 0.000000001);
        assertEquals(answer.getY(), -2.0, 0.000000001);
    }

    @Test
    void multiply() {
        Vec2d answer = one.multiply(one);

        assertEquals(answer.getX(), 1.0, 0.000000001);
        assertEquals(answer.getY(), 4.0, 0.000000001);
    }

    @Test
    void cross() {
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
    void get() {
        assertEquals(one.get(0), 1.0, 0.000000001);
        assertEquals(one.get(1), 2.0, 0.000000001);
    }

    @Test
    void toVecf() {
        Vec2f answer = one.toVecf();

        assertEquals(answer.getX(), 1.0, 0.000000001);
        assertEquals(answer.getY(), 2.0, 0.000000001);
    }
}