package com.nick.wood.maths.objects;

public class Vecd {

    private final int SIZE;

    private final double[] elements;

    public Vecd(double ... e) {
        elements = e;
        SIZE = e.length;
    }

    /**
     * Default matrices to choose from
     * - Identity
     */
    public static Vecd Empty(int size) {
        return new Vecd(0.0, 0.0, 0.0);
    }

    public double dot(Vecd vec) {
        assert SIZE == vec.elements.length;
        double sum = 0.0;
        for (int i = 0; i < SIZE; i++) {
            sum += this.elements[i] * vec.elements[i];
        }
        return sum;
    }

    /*public Vecd cross(Vecd vec) {
        assert SIZE == vec.elements.length;
        for (int i = 0; i < SIZE; i++) {
            this.elements[i]
        }
    }*/
}
