package com.nick.wood.maths.objects;

import java.util.Arrays;
import java.util.function.BiFunction;

public class Matrixd {

    private final int SIZE;

    private final double[] elements;

    /**
     * Default matrices to choose from
     * - Identity
     * - Empty
     */
    public static Matrixd Identity(int size) {
        return new Matrixd(loopAction((i, j) -> (i.equals(j)) ? 1.0 : 0.0, size));
    }

    public static Matrixd Empty(int size) {
        return new Matrixd(loopAction((i, j) -> 0.0, size));
    }

    private static double[] loopAction(BiFunction<Integer, Integer, Double> func, int size) {

        double[] elems = new double[size * size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                elems[i * size + j] = func.apply(i, j);
            }
        }

        return elems;

    }

    public Matrixd(double... elements) {
        assert elements.length > 3;
        double sqrtD = Math.sqrt(elements.length);
        int sqrtI = (int) sqrtD;
        double diff = sqrtD - sqrtI;
        if (diff > 0.00000001) {
            System.err.println("Number of elements provided does not have a square root and will not make a square matrix. A few elements have been culled.");
        }
        SIZE = sqrtI;
        assert elements.length == SIZE * SIZE;
        this.elements = elements;
    }

    /**
     * Getter using x and y values assuming x are columns are y are rows.
     *
     * @param x x index
     * @param y y index
     * @return values in matrix
     */
    public double get(int x, int y) {
        return elements[y * SIZE + x];
    }

    /**
     * Function to add matrices together
     *
     * @param mat input matrix
     * @return new matrix summing values of 2 matrices.
     */
    public Matrixd add(Matrixd mat) {
        assert elements.length == mat.elements.length;
        return new Matrixd(loopAction((i, j) -> get(i, j) + mat.get(i, j), SIZE));
    }

    /**
     * Adds a 2D vector to the diagonal elements of the matrix
     *
     * @param vec2d input vector
     * @return new Matrix2D
     */
    public Matrixd add(Vec2d vec2d) {
        return new Matrixd(loopAction((i, j) -> get(i, j) + vec2d.get(i), SIZE));
    }

    /**
     * Scale matrix by value
     *
     * @param s
     * @return matrix2d
     */
    public Matrixd scale(double s) {
        return new Matrixd(loopAction((i, j) -> get(i, j) * s, SIZE));
    }

    /**
     * Matrix element-wise multiplication
     *
     * @param matrixd
     * @return matrix2d
     */
    public Matrixd multiply(Matrixd matrixd) {
        return new Matrixd(loopAction((i, j) -> get(i, j) * matrixd.get(j, i) +
                get(i, j) * matrixd.get(j, i), SIZE));
    }

    /**
     * Matrix vector multiplication
     *
     * @param vec
     * @return matrix2d
     */
    public Matrixd multiply(Vec2d vec) {
        return new Matrixd(loopAction((i, j) -> get(i, j) * vec.get(i) +
                get(i + 1, j + 1) * vec.get(i), SIZE));

    }

    /**Determinant
     *
     * @return double
     */
    public double det() {
        return det(this, SIZE);
    }

    /**Recursive implementation
     *
     * @param matrix
     * @param size
     * @return
     */
    private double det(Matrixd matrix, int size) {

        if (size == 2) {
            return (matrix.get(0, 0) * matrix.get(0, 1)) - (matrix.get(1, 0) * matrix.get(1, 1));
        } else {
            int sign = 1;
            double sum = 0.0;
            for (int i = 0; i < size; i++) {
                int newSize = size - 1;
                double[] newMatrix = new double[newSize * newSize];
                int index = 0;
                for (int y = 1; y < size; y++) {
                    for (int x = 0; x < i; x++) {
                        newMatrix[index++] = matrix.get(x, y);
                    }
                    for (int x = i + 1; x < size; x++) {
                        newMatrix[index++] = matrix.get(x, y);
                    }
                }
                sum += sign * matrix.get(i, 0) * det(new Matrixd(newMatrix), newSize);
                sign *= -1;
            }
            return sum;
        }
    }

    // for testing
    double[] getElements() {
        return elements;
    }
}
