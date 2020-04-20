package com.nick.wood.maths.objects.matrix;

import com.nick.wood.maths.objects.vector.Vec2d;

import java.util.Arrays;

public class Matrix2d {

    private final static int SIZE = 2;

    private final double[] elements;

    /** Default matrices to choose from
     * - Identity
     * - Empty
     */
    public static Matrix2d Identity = new Matrix2d(1.0, 0.0, 0.0, 1.0);
    public static Matrix2d Empty = new Matrix2d(0.0, 0.0, 0.0, 0.0);

    /**Constructor taking in 4 double values
     *
     * @param elements must be 4 double values
     */
    public Matrix2d(double... elements) {
        assert elements.length == SIZE * SIZE;
        this.elements = elements;
    }

    /**Getter using x and y values assuming x are columns are y are rows.
     *
     * @param x x index
     * @param y y index
     * @return values in matrix
     */
    public double get(int x, int y) {
        return elements[x * SIZE + y];
    }

    /**Function to add matrices together
     *
     * @param mat input matrix
     * @return new matrix summing values of 2 matrices.
     */
    public Matrix2d add(Matrix2d mat) {
        return new Matrix2d(
                get(0, 0) + mat.get(0, 0),
                get(1, 0) + mat.get(1, 0),
                get(0, 1) + mat.get(0, 1),
                get(1, 1) + mat.get(1, 1)
        );
    }

    /**Adds a 2D vector to the diagonal elements of the matrix
     *
     * @param vec2d input vector
     * @return new Matrix2D
     */
    public Matrix2d add(Vec2d vec2d) {
        return new Matrix2d(
                get(0, 0) + vec2d.getX(), get(1, 0),
                get(0, 1), get(1, 0) + vec2d.getY()
        );
    }

    /**Scale matrix by value
     *
     * @param s
     * @return matrix2d
     */
    public Matrix2d scale(double s) {
        return new Matrix2d(
                Arrays.stream(this.elements).map((val -> val * s)).toArray()
        );
    }

    /**Matrix element-wise multiplication
     *
     * @param matrix2d
     * @return matrix2d
     */
    public Matrix2d multiply(Matrix2d matrix2d) {

        double[] newElements = new double[SIZE*SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newElements[i * SIZE + j] =
                        get(i, j) * matrix2d.get(j, i)+
                                get(i + 1, j + 1) * matrix2d.get(j + 1, i + 1);
            }
        }

        return new Matrix2d(newElements);

    }

    /**Matrix vector multiplication
     *
     * @param vec
     * @return matrix2d
     */
    public Matrix2d multiply(Vec2d vec) {

        double[] newElements = new double[SIZE*SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newElements[i * SIZE + j] =
                        get(i, j) * vec.getX() +
                                get(i + 1, j + 1) * vec.getY();
            }
        }

        return new Matrix2d(newElements);

    }

    /**Determinant
     *
     * @return double
     */
    public double det() {

        return (get(0, 0) * get(1, 1)) - (get(1, 0) * get(0, 1));

    }



}
