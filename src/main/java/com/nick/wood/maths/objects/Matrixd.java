package com.nick.wood.maths.objects;

import java.util.Arrays;
import java.util.function.BiFunction;

public class Matrixd {

    private final int SIZE_ROW;
    private final int SIZE_COL;

    private final double[] elements;

    /**
     * Default matrices to choose from
     * - Identity
     * - Empty
     */
    public static Matrixd Identity(int size) {
        return new Matrixd(loopAction((i, j) -> (i.equals(j)) ? 1.0 : 0.0, size, size));
    }

    public static Matrixd Empty(int size) {
        return new Matrixd(loopAction((i, j) -> 0.0, size, size));
    }

    private static double[] loopAction(BiFunction<Integer, Integer, Double> func, int sizeRow, int sizeCol) {

        double[] elems = new double[sizeRow * sizeCol];

        for (int i = 0; i < sizeCol; i++) {
            for (int j = 0; j < sizeRow; j++) {
                elems[i * sizeCol + j] = func.apply(i, j);
            }
        }

        return elems;

    }

    /**For square matrices
     *
     * @param elements
     */
    public Matrixd(double... elements) {
        assert elements.length > 3;
        double sqrtD = Math.sqrt(elements.length);
        int sqrtI = (int) sqrtD;
        double diff = sqrtD - sqrtI;
        if (diff > 0.00000001) {
            System.err.println("Number of elements provided does not have a square root and will not make a square matrix. A few elements have been culled.");
        }
        SIZE_ROW = sqrtI;
        SIZE_COL = sqrtI;
        assert elements.length == SIZE_ROW * SIZE_COL;
        this.elements = elements;
    }

    /**For non square matrices
     *
     * @param rowSize
     * @param colSize
     * @param elements
     */
    public Matrixd(int rowSize, int colSize, double... elements) {
        assert elements.length > 3;
        double sqrtD = Math.sqrt(elements.length);
        int sqrtI = (int) sqrtD;
        double diff = sqrtD - sqrtI;
        if (diff > 0.00000001) {
            System.err.println("Number of elements provided does not have a square root and will not make a square matrix. A few elements have been culled.");
        }
        SIZE_ROW = sqrtI;
        SIZE_COL = sqrtI;
        assert elements.length == SIZE_ROW * SIZE_COL;
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
        return elements[y * SIZE_COL + x];
    }

    private Vecd getRow(int rowIndex) {
        return new Vecd(loopAction((i, j) -> get(i, rowIndex), SIZE_ROW, 1));
    }

    private Vecd getCol(int colIndex) {
        return new Vecd(loopAction((i, j) -> get(colIndex, j), 1, SIZE_COL));
    }

    /**
     * Adds a 2D vector to the diagonal elements of the matrix
     *
     * @param vec2d input vector
     * @return new Matrix2D
     */
    public Matrixd add(Vec2d vec2d) {
        return new Matrixd(loopAction((i, j) -> get(i, j) + vec2d.get(i), SIZE_ROW, SIZE_COL));
    }

    /**
     * Adds 2 matrices together
     *
     * @param matrixd input matrix
     * @return new Matrix2D
     */
    public Matrixd add(Matrixd matrixd) {
        assert SIZE_ROW == matrixd.SIZE_ROW;
        assert SIZE_COL == matrixd.SIZE_COL;
        return new Matrixd(loopAction((i, j) -> matrixd.get(i, j) + matrixd.get(j, i), SIZE_ROW, SIZE_COL));
    }

    /**
     * Scale matrix by value
     *
     * @param s
     * @return matrix2d
     */
    public Matrixd scale(double s) {
        return new Matrixd(loopAction((i, j) -> get(i, j) * s, SIZE_ROW, SIZE_COL));
    }

    /**
     * Matrix element-wise multiplication
     *
     * @param matrixd
     * @return matrix2d
     */
    public Matrixd multiply(Matrixd matrixd) {
        return new Matrixd(loopAction((i, j) -> get(i, j) * matrixd.get(i, j), SIZE_ROW, SIZE_COL));
    }

    /**
     * Matrix vector multiplication
     *
     * @param vec
     * @return matrix2d
     */
    public Matrixd multiply(Vec2d vec) {
        return new Matrixd(loopAction((i, j) -> get(i, j) * vec.get(i) +
                get(i + 1, j + 1) * vec.get(i), SIZE_ROW, SIZE_COL));
    }

    /**
     * Function to dot product matrices together
     *
     * @param mat input matrix
     * @return new matrix.
     */
    /*public Matrixd dot(Matrixd mat) {
        assert elements.length == mat.elements.length;

        double[] newElems = new double[SIZE_ROW * SIZE_COL];

        for (int rowIndex = 0; rowIndex < elements.length; rowIndex++) {
            for (int colIndex = 0; colIndex < elements.length; colIndex++) {
                newElems[rowIndex][colIndex] = this.getRow(rowIndex).dot(mat.getCol(colIndex));
            }
        }
    }*/

    /**Determinant
     *
     * @return double
     */
    public double det() {
        assert SIZE_ROW == SIZE_COL;
        return det(this, SIZE_ROW);
    }

    /**Recursive implementation
     *
     * @param matrix
     * @param size
     * @return
     */
    private double det(Matrixd matrix, int size) {

        if (size == 2) {
            return (matrix.get(0, 0) * matrix.get(1, 1)) - (matrix.get(1, 0) * matrix.get(0, 1));
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
