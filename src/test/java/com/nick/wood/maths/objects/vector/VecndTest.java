package com.nick.wood.maths.objects.vector;

import com.nick.wood.maths.objects.dev.Vecnd;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VecndTest {

    Vecnd one = new Vecnd(1.0);
    Vecnd two = new Vecnd(1.0, 2.0);
    Vecnd three = new Vecnd(1.0, 2.0, 3.0);
    Vecnd four = new Vecnd(1.0, 2.0, 3.0, 4.0);
    Vecnd five = new Vecnd(1.0, 2.0, 3.0, 4.0, 5.0);

    @Test
    void empty() {
    }

    @Test
    void dot() {
        double oneDot = one.dot(one);
        double twoDot = two.dot(two);
        double threeDot = three.dot(three);
        double fourDot = four.dot(four);
        double fiveDot = five.dot(five);

        assertEquals(1.0, oneDot, 0.000001);
        assertEquals(5.0, twoDot, 0.000001);
        assertEquals(14.0, threeDot, 0.000001);
        assertEquals(30.0, fourDot, 0.000001);
        assertEquals(55.0, fiveDot, 0.000001);
    }
}