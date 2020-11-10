package com.boc_dev.maths.objects.dev;

public class Vecnd {

    private final int SIZE;

    private final double[] elements;

    public Vecnd(double ... e) {
        elements = e;
        SIZE = e.length;
    }

    /**
     * Default matrices to choose from
     * - Identity
     */
    public static Vecnd Empty(int size) {
        return new Vecnd(0.0, 0.0, 0.0);
    }

    public double dot(Vecnd vec) {
        assert SIZE == vec.elements.length;
        double sum = 0.0;
        for (int i = 0; i < SIZE; i++) {
            sum += this.elements[i] * vec.elements[i];
        }
        return sum;
    }

    /**
     * Getter using x and y values assuming x are columns are y are rows.
     *
     * @return values in matrix
     */
    public double get(int x) {
        return elements[x];
    }

}
