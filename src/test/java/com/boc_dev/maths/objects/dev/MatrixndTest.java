package com.boc_dev.maths.objects.dev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixndTest {

    private final Matrixnd one = new Matrixnd(4.0, 6, 3, 8);
    private final Matrixnd two = new Matrixnd(6.0, 1, 1, 4, -2, 5, 2, 8, 7);
    private final Matrixnd three = new Matrixnd(1.0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
    private final Matrixnd four = new Matrixnd(1.0, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7);


    @Test
    void identity() {
    }

    @Test
    void empty() {
    }

    @Test
    void get() {
        assertEquals(one.get(0, 0), 4.0, 0.00000001);
        assertEquals(one.get(1, 0), 6.0, 0.00000001);
        assertEquals(one.get(0, 1), 3.0, 0.00000001);
        assertEquals(one.get(1, 1), 8.0, 0.00000001);

        assertEquals(two.get(0, 0), 6.0, 0.00000001);
        assertEquals(two.get(1, 0), 1.0, 0.00000001);
        assertEquals(two.get(2, 0), 1.0, 0.00000001);
        assertEquals(two.get(0, 1), 4.0, 0.00000001);
        assertEquals(two.get(1, 1), -2.0, 0.00000001);
        assertEquals(two.get(2, 1), 5.0, 0.00000001);
        assertEquals(two.get(0, 2), 2.0, 0.00000001);
        assertEquals(two.get(1, 2), 8.0, 0.00000001);
        assertEquals(two.get(2, 2), 7.0, 0.00000001);
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
    void multiplyElementsMatrixTest() {

        Matrixnd oneMul = one.elemMultiply(one);
        Matrixnd oneMulAnswer = new Matrixnd(16.0, 36, 9, 64);
        Matrixnd twoMul = two.elemMultiply(two);
        Matrixnd twoMulAnswer = new Matrixnd(36.0, 1, 1, 16, 4, 25, 4, 64, 49);
        Matrixnd threeMul = three.elemMultiply(three);
        Matrixnd threeMulAnswer = new Matrixnd(1.0, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 12*12, 13*13, 14*14, 15*15, 16*16);
        Matrixnd fourMul = four.elemMultiply(four);
        Matrixnd fourMulAnswer = new Matrixnd(1.0, 4, 9, 16, 25, 36, 49, 64, 81, 1.0, 4, 9, 16, 25, 36, 49, 64, 81, 1.0, 4, 9, 16, 25, 36, 49);

        assertArrayEquals(oneMul.getElements(), oneMulAnswer.getElements(), 0.00000001);
        assertArrayEquals(twoMul.getElements(), twoMulAnswer.getElements(), 0.00000001);
        assertArrayEquals(threeMul.getElements(), threeMulAnswer.getElements(), 0.00000001);
        assertArrayEquals(fourMul.getElements(), fourMulAnswer.getElements(), 0.00000001);

    }

    @Test
    void multiplyMatrixTest() {

        Matrixnd oneMul = one.multiply(one);
        Matrixnd oneMulAnswer = new Matrixnd(34.0, 72, 36, 82);
        Matrixnd twoMul = two.multiply(two);
        Matrixnd twoMulAnswer = new Matrixnd(42.0, 12, 18, 26, 48, 29, 58, 42, 91);
        Matrixnd threeMul = three.multiply(three);
        Matrixnd threeMulAnswer = new Matrixnd(90.0, 100, 110, 120, 202, 228, 254, 280, 314, 356, 398, 440, 426, 484, 542, 600);
        Matrixnd fourMul = four.multiply(four);
        Matrixnd fourMulAnswer = new Matrixnd(62.0, 77, 92, 71, 68, 130, 161, 192, 142, 110, 81, 101, 121, 96, 89, 86, 113, 140, 158, 113, 100, 125, 150, 121, 110);

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

    @Test
    void getCol() {

        Vecnd oneColOne = one.getCol(0);
        Vecnd oneColTwo = one.getCol(1);
        Vecnd oneRowOne = one.getRow(0);
        Vecnd oneRowTwo = one.getRow(1);

        assertEquals(oneColOne.get(0), 4.0, 0.0000000001);
        assertEquals(oneColOne.get(1), 3.0, 0.0000000001);
        assertEquals(oneColTwo.get(0), 6.0, 0.0000000001);
        assertEquals(oneColTwo.get(1), 8.0, 0.0000000001);
        assertEquals(oneRowOne.get(0), 4.0, 0.0000000001);
        assertEquals(oneRowOne.get(1), 6.0, 0.0000000001);
        assertEquals(oneRowTwo.get(0), 3.0, 0.0000000001);
        assertEquals(oneRowTwo.get(1), 8.0, 0.0000000001);
    }
}