package com.nick.wood.maths.objects.matrix;

import com.nick.wood.maths.objects.vector.Vec2f;

public class Matrix2f {

    private final static int SIZE = 2;

    private final float[] elements;

    /** Default matrices to choose from
     * - Identity
     * - Empty
     */
    public static Matrix2f Identity = new Matrix2f(1.0f, 0.0f, 0.0f, 1.0f);
    public static Matrix2f Empty = new Matrix2f(0.0f, 0.0f, 0.0f, 0.0f);

    /**Constructor taking in 4 float values
     *
     * @param elements must be 4 float values
     */
    public Matrix2f(float... elements) {
        assert elements.length == SIZE * SIZE;
        this.elements = elements;
    }

    /**Getter using x and y values assuming x are columns are y are rows.
     *
     * @param x x index
     * @param y y index
     * @return values in matrix
     */
    public float get(int x, int y) {
        return elements[x * SIZE + y];
    }

    /**Function to add matrices together
     *
     * @param mat input matrix
     * @return new matrix summing values of 2 matrices.
     */
    public Matrix2f add(Matrix2f mat) {
        return new Matrix2f(
                get(0, 0) + mat.get(0, 0),
                get(1, 0) + mat.get(1, 0),
                get(0, 1) + mat.get(0, 1),
                get(1, 1) + mat.get(1, 1)
        );
    }

    /**Adds a 2D vector to the diagonal elements of the matrix
     *
     * @param Vec2f input vector
     * @return new Matrix2D
     */
    public Matrix2f add(Vec2f Vec2f) {
        return new Matrix2f(
                get(0, 0) + Vec2f.getX(), get(1, 0),
                get(0, 1), get(1, 0) + Vec2f.getY()
        );
    }

    /**Scale matrix by value
     *
     * @param s
     * @return matrix2d
     */
    public Matrix2f scale(float s) {
        float[] newElems = new float[this.elements.length];
        for (int i = 0; i < this.elements.length; i++) {
            newElems[i] = elements[i] * s;
        }
        return new Matrix2f(newElems);
    }

    /**Matrix element-wise multiplication
     *
     * @param matrix2d
     * @return matrix2d
     */
    public Matrix2f multiply(Matrix2f matrix2d) {

        float[] newElements = new float[SIZE*SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newElements[i * SIZE + j] =
                        get(i, j) * matrix2d.get(j, i)+
                                get(i + 1, j + 1) * matrix2d.get(j + 1, i + 1);
            }
        }

        return new Matrix2f(newElements);

    }

    /**Matrix vector multiplication
     *
     * @param vec
     * @return matrix2d
     */
    public Matrix2f multiply(Vec2f vec) {

        float[] newElements = new float[SIZE*SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newElements[i * SIZE + j] =
                        get(i, j) * vec.getX() +
                                get(i + 1, j + 1) * vec.getY();
            }
        }

        return new Matrix2f(newElements);

    }

    /**Determinant
     *
     * @return float
     */
    public float det() {

        return (get(0, 0) * get(1, 1)) - (get(1, 0) * get(0, 1));

    }



}
