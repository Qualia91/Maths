package com.nick.wood.maths.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixdTest {

    private final Matrixd one = new Matrixd(4, 6, 3, 8);
    private final Matrixd two = new Matrixd(6, 1, 1, 4, -2, 5, 2, 8, 7);
    private final Matrixd three = new Matrixd(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
    private final Matrixd four = new Matrixd(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7);


    @Test
    void identity() {
    }

    @Test
    void empty() {
    }

    @Test
    void get() {
    }

    @Test
    void add() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void scale() {
    }

    @Test
    void multiply() {
    }

    @Test
    void multiplyMatrixTest() {

        Matrixd oneMul = one.multiply(one);
        Matrixd oneMulAnswer = new Matrixd(34, 72, 36, 82);
        Matrixd twoMul = two.multiply(two);
        Matrixd twoMulAnswer = new Matrixd(42, 12, 18, 26, 48, 29, 58, 42, 91);
        Matrixd threeMul = three.multiply(three);
        Matrixd threeMulAnswer = new Matrixd(90, 100, 110, 120, 202, 228, 254, 280, 314, 356, 398, 440, 426, 484, 542, 600);
        Matrixd fourMul = four.multiply(four);
        Matrixd fourMulAnswer = new Matrixd(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);

        assertArrayEquals(oneMul.getElements(), oneMulAnswer.getElements(), 0.00000001);
        assertArrayEquals(twoMul.getElements(), twoMulAnswer.getElements(), 0.00000001);
        assertArrayEquals(threeMul.getElements(), threeMulAnswer.getElements(), 0.00000001);
        assertArrayEquals(fourMul.getElements(), fourMulAnswer.getElements(), 0.00000001);

    }

    @Test
    void detTest() {
        assertEquals(one.det(), 14, 0.0000000001);
        assertEquals(two.det(), -306, 0.0000000001);
        assertEquals(three.det(), 0.0, 0.0000000001);
        assertEquals(four.det(), 0.0, 0.0000000001);
    }
}