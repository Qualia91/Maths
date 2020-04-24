package com.nick.wood.maths.objects.vector;

import com.nick.wood.maths.objects.matrix.Matrix2f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Vec2fTest {

    private final Vec2f one = new Vec2f(1, 2);
    private final Vec2f two = new Vec2f(3, 4);

    @Test
    void getXTest() {
        assertEquals(one.getX(), 1.0f, 0.000000001f);
    }

    @Test
    void getYTest() {
        assertEquals(one.getY(), 2.0f, 0.000000001f);
    }

    @Test
    void addTest() {
        Vec2f answer = one.add(one);

        assertEquals(answer.getX(), 2.0f, 0.000000001f);
        assertEquals(answer.getY(), 4.0f, 0.000000001f);
    }

    @Test
    void subtractTest() {
        Vec2f answer = one.subtract(one);

        assertEquals(answer.getX(), 0.0, 0.000000001);
        assertEquals(answer.getY(), 0.0, 0.000000001);
    }

    @Test
    void scaleTest() {
        Vec2f answer = one.scale(10.0f);

        assertEquals(answer.getX(), 10.0, 0.000000001);
        assertEquals(answer.getY(), 20.0, 0.000000001);
    }

    @Test
    void dotTest() {
        float answer = one.dot(one);

        assertEquals(answer, 5.0, 0.000000001);
    }

    @Test
    void length2Test() {
        float answer = one.length2();

        assertEquals(answer, 5.0, 0.000000001);
    }

    @Test
    void lengthTest() {
        float answer = one.length();

        assertEquals(answer, Math.sqrt(5.0), 0.000000001);
    }

    @Test
    void normaliseTest() {
        Vec2f answer = one.normalise();

        assertEquals(answer.getX(), 1.0/Math.sqrt(5.0), 0.000000001);
        assertEquals(answer.getY(), 2.0/Math.sqrt(5.0), 0.000000001);

        // test length being 0
        Vec2f answer2 = Vec2f.ZERO.normalise();

        assertEquals(answer2.getX(), 0.0, 0.000000001);
        assertEquals(answer2.getY(), 0.0, 0.000000001);
    }

    @Test
    void getValuesTest() {
        float[] answer = one.getValues();

        assertEquals(answer.length, 2);
        assertEquals(answer[0], 1.0, 0.000000001);
        assertEquals(answer[1], 2.0, 0.000000001);
    }

    @Test
    void outerProductTest() {
        Matrix2f matrix2f = one.outerProduct(one);

        assertEquals(matrix2f.get(0, 0), 1.0, 0.000000001);
        assertEquals(matrix2f.get(1, 0), 2.0, 0.000000001);
        assertEquals(matrix2f.get(0, 1), 2.0, 0.000000001);
        assertEquals(matrix2f.get(1, 1), 4.0, 0.000000001);
    }

    @Test
    void negTest() {
        Vec2f answer = one.neg();

        assertEquals(answer.getX(), -1.0, 0.000000001);
        assertEquals(answer.getY(), -2.0, 0.000000001);
    }

    @Test
    void multiplyTest() {
        Vec2f answer = one.multiply(one);

        assertEquals(answer.getX(), 1.0, 0.000000001);
        assertEquals(answer.getY(), 4.0, 0.000000001);
    }

    @Test
    void crossTest() {
        Vec2f answer = one.cross(one);

        assertEquals(answer.getX(), 2.0, 0.000000001);
        assertEquals(answer.getY(), 2.0, 0.000000001);
    }

    @Test
    void midpoint() {
        Vec2f answer = one.midpoint(two);

        assertEquals(answer.getX(), 2.0, 0.000000001);
        assertEquals(answer.getY(), 3.0, 0.000000001);
    }

    @Test
    void getTest() {
        assertEquals(one.get(0), 1.0, 0.000000001);
        assertEquals(one.get(1), 2.0, 0.000000001);
    }

    @Test
    void toVecdTest() {
        Vec2d answer = one.toVecd();

        assertEquals(answer.getX(), 1.0, 0.000000001);
        assertEquals(answer.getY(), 2.0, 0.000000001);
    }

    @Test 
    void equalsTest() {
        Vec2f a = new Vec2f(1, 2);
        Vec2f b = new Vec2f(1, 2);

        assertEquals(a, b);
    }
}