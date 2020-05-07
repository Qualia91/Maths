package com.nick.wood.maths.objects.dev;

import com.nick.wood.maths.objects.vector.Vec2d;
import com.nick.wood.maths.objects.vector.Vecd;

import java.util.function.BiFunction;

public class Matrixnd {

    private final int SIZE_ROW;
    private final int SIZE_COL;

    private final double[] elements;

    /**
     * Default matrices to choose from
     * - Identity
     * - Empty
     */
    public static Matrixnd Identity(int size) {
        return new Matrixnd(loopAction((i, j) -> (i.equals(j)) ? 1.0 : 0.0, size, size));
    }

    public static Matrixnd Empty(int size) {
        return new Matrixnd(loopAction((i, j) -> 0.0, size, size));
    }

    private static double[] loopAction(BiFunction<Integer, Integer, Double> func, int sizeRow, int sizeCol) {

        double[] elems = new double[sizeRow * sizeCol];

        for (int rowIndex = 0; rowIndex < sizeRow; rowIndex++) {
            for (int colIndex = 0; colIndex < sizeCol; colIndex++) {
                elems[rowIndex * sizeCol + colIndex] = func.apply(colIndex, rowIndex);
            }
        }

        return elems;

    }

    /**For square matrices
     *
     * @param elements
     */
    public Matrixnd(double... elements) {
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
    public Matrixnd(int rowSize, int colSize, double... elements) {
        assert elements.length > 3;
        SIZE_ROW = rowSize;
        SIZE_COL = colSize;
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

    public Vecnd getRow(int rowIndex) {
        return new Vecnd(loopAction((i, j) -> get(j, rowIndex), SIZE_ROW, 1));
    }

    public Vecnd getCol(int colIndex) {
        return new Vecnd(loopAction((i, j) -> get(colIndex, i), 1, SIZE_COL));
    }

    /**
     * Adds a 2D vector to the diagonal elements of the matrix
     *
     * @param vecd input vector
     * @return new Matrix2D
     */
    public Matrixnd add(Vecd vecd) {
        return new Matrixnd(loopAction((i, j) -> get(i, j) + vecd.get(i), SIZE_ROW, SIZE_COL));
    }

    /**
     * Adds 2 matrices together
     *
     * @param matrixnd input matrix
     * @return new Matrix2D
     */
    public Matrixnd add(Matrixnd matrixnd) {
        assert SIZE_ROW == matrixnd.SIZE_ROW;
        assert SIZE_COL == matrixnd.SIZE_COL;
        return new Matrixnd(loopAction((i, j) -> matrixnd.get(i, j) + matrixnd.get(j, i), SIZE_ROW, SIZE_COL));
    }

    /**
     * Scale matrix by value
     *
     * @param s
     * @return matrix2d
     */
    public Matrixnd scale(double s) {
        return new Matrixnd(loopAction((i, j) -> get(i, j) * s, SIZE_ROW, SIZE_COL));
    }

    /**
     * Matrix element-wise multiplication
     *
     * @param matrixnd
     * @return matrixd
     */
    public Matrixnd elemMultiply(Matrixnd matrixnd) {
        return new Matrixnd(loopAction((i, j) -> get(i, j) * matrixnd.get(i, j), SIZE_ROW, SIZE_COL));
    }

    /**
     * Matrix multiplication
     *
     * @param matrixnd
     * @return matrix2d
     */
    public Matrixnd multiply(Matrixnd matrixnd) {
        return new Matrixnd(loopAction((i, j) -> getCol(i).dot(matrixnd.getRow(j)), SIZE_ROW, SIZE_COL));
    }

    /**
     * Matrix vector multiplication
     *
     * @param vec
     * @return matrix2d
     */
    public Matrixnd multiply(Vec2d vec) {
        return new Matrixnd(loopAction((i, j) -> get(i, j) * vec.get(i) +
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
    private double det(Matrixnd matrix, int size) {

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
                sum += sign * matrix.get(i, 0) * det(new Matrixnd(newMatrix), newSize);
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
