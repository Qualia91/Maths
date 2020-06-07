package com.nick.wood.maths.objects.matrix;

import com.nick.wood.maths.objects.vector.Vec3f;

import java.util.Arrays;

public class Matrix3f {

	public static final int SIZE = 3;
	private final float[] elements;

	public static Matrix3f Identity = new Matrix3f(
			1, 0, 0,
			0, 1, 0,
			0, 0, 1
	);

	public static Matrix3f Rotation(float angle, Vec3f axis) {
		float[] newElems = new float[9];

		float cos = (float) Math.cos(Math.toRadians(angle));
		float sin = (float) Math.sin(Math.toRadians(angle));
		float C = 1 - cos;

		newElems[0 * SIZE + 0] = cos + axis.getX() * axis.getX() * C;
		newElems[1 * SIZE + 0] = axis.getX() * axis.getY() * C - axis.getZ() * sin;
		newElems[2 * SIZE + 0] = axis.getX() * axis.getZ() * C + axis.getY() * sin;
		newElems[0 * SIZE + 1] = axis.getY() * axis.getX() * C + axis.getZ() * sin;
		newElems[1 * SIZE + 1] = cos + axis.getY() * axis.getY() * C;
		newElems[2 * SIZE + 1] = axis.getY() * axis.getZ() * C - axis.getX() * sin;
		newElems[0 * SIZE + 2] = axis.getZ() * axis.getX() * C - axis.getY() * sin;
		newElems[1 * SIZE + 2] = axis.getZ() * axis.getY() * C + axis.getX() * sin;
		newElems[2 * SIZE + 2] = cos + axis.getZ() * axis.getZ() * C;

		return new Matrix3f(newElems);
	}

	private static Matrix3f Scale(Vec3f scale) {
		return new Matrix3f(
				scale.getX(), 0, 0,
				0, scale.getY(), 0,
				0, 0, scale.getZ()
		);
	}

	public Matrix3f add(Matrix3f matrix) {

		float[] newElements = new float[9];

		for (int i = 0; i < this.elements.length; i++) {
			newElements[i] = this.elements[i] + matrix.getValues()[i];
		}

		return new Matrix3f(newElements);
	}

	public Matrix3f add(Vec3f vec3d) {
		return new Matrix3f(
				elements[0] + vec3d.getX(), elements[1], elements[2],
				elements[4], elements[5] + vec3d.getY(), elements[6],
				elements[8], elements[9], elements[10] + vec3d.getZ()
		);
	}

	public Matrix3f multiply(Matrix3f matrix4d) {

		float[] newElements = new float[9];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				newElements[j * SIZE + i] =
						this.elements[i + SIZE * 0] * matrix4d.getValues()[0 + j * SIZE] +
								this.elements[i + SIZE * 1] * matrix4d.getValues()[1 + j * SIZE] +
								this.elements[i + SIZE * 2] * matrix4d.getValues()[2 + j * SIZE];
			}
		}

		return new Matrix3f(newElements);

	}

	public Vec3f multiply(Vec3f vec) {
		return new Vec3f(
				(vec.getX() * this.elements[0]) + (vec.getY() * this.elements[1]) + (vec.getZ() * this.elements[2]),
				(vec.getX() * this.elements[3]) + (vec.getY() * this.elements[4]) + (vec.getZ() * this.elements[5]),
				(vec.getX() * this.elements[6]) + (vec.getY() * this.elements[7]) + (vec.getZ() * this.elements[8])
		);
	}

	public Matrix3f scale(float s) {
        return new Matrix3f(
                get(0,0) * s, get(1,0) * s, get(2,0) * s,
                get(0,1) * s, get(1,1) * s, get(2,1) * s,
                get(0,2) * s, get(1,2) * s, get(2,2) * s
        );
	}

	public Matrix3f(float... elements) {
		assert elements.length == 9;
		this.elements = elements;
	}

	public float get(int x, int y) {
		return elements[y * SIZE + x];
	}

	private float[] getValues() {
		return elements;
	}

	public double[] getValuesD() {
		return new double[]{
				elements[0],
				elements[1],
				elements[2],
				elements[3],
				elements[4],
				elements[5],
				elements[6],
				elements[7],
				elements[8],
				elements[9]
		};
	}

	public float trace() {
		return elements[0] + elements[5] + elements[10];
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Matrix3f matrix3f = (Matrix3f) o;
		return Arrays.equals(elements, matrix3f.elements);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(elements);
	}

	public Matrix3f transpose() {

		float[] newElements = new float[9];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				newElements[j * SIZE + i] = this.getValues()[i * SIZE + j];
			}
		}

		return new Matrix3f(newElements);
	}

	public Vec3f getXVec() {
		return new Vec3f(get(0,0), get(1,0), get(2,0));
	}

	public Vec3f getYVec() {
		return new Vec3f(get(0,1), get(1,1), get(2,1));
	}

	public Vec3f getZVec() {
		return new Vec3f(get(0,2), get(1,2), get(2,2));
	}

}
