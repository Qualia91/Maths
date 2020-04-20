package com.nick.wood.maths.objects.matrix;

import com.nick.wood.maths.objects.vector.Vec3d;

import java.util.Arrays;

public class Matrix3d {

	public static final int SIZE = 3;
	private final double[] elements;

	public static Matrix3d Identity = new Matrix3d(
			1.0, 0.0, 0.0,
			0.0, 1.0, 0.0,
			0.0, 0.0, 1.0
	);

	public static Matrix3d Rotation(double angle, Vec3d axis) {
		double[] newElems = new double[9];

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

		return new Matrix3d(newElems);
	}

	private static Matrix3d Scale(Vec3d scale) {
		return new Matrix3d(
				scale.getX(), 0.0, 0.0,
				0.0, scale.getY(), 0.0,
				0.0, 0.0, scale.getZ()
		);
	}

	public Matrix3d add(Matrix3d matrix) {

		double[] newElements = new double[9];

		for (int i = 0; i < this.elements.length; i++) {
			newElements[i] = this.elements[i] + matrix.getValues()[i];
		}

		return new Matrix3d(newElements);
	}

	public Matrix3d add(Vec3d vec3d) {
		return new Matrix3d(
				elements[0] + vec3d.getX(), elements[1], elements[2],
				elements[4], elements[5] + vec3d.getY(), elements[6],
				elements[8], elements[9], elements[10] + vec3d.getZ()
		);
	}

	public Matrix3d multiply(Matrix3d matrix4d) {

		double[] newElements = new double[9];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				newElements[j * SIZE + i] =
						this.elements[i + SIZE * 0] * matrix4d.getValues()[0 + j * SIZE] +
								this.elements[i + SIZE * 1] * matrix4d.getValues()[1 + j * SIZE] +
								this.elements[i + SIZE * 2] * matrix4d.getValues()[2 + j * SIZE];
			}
		}

		return new Matrix3d(newElements);

	}

	public Vec3d multiply(Vec3d vec) {
		return new Vec3d(
				(vec.getX() * this.elements[0]) + (vec.getY() * this.elements[1]) + (vec.getZ() * this.elements[2]),
				(vec.getX() * this.elements[3]) + (vec.getY() * this.elements[4]) + (vec.getZ() * this.elements[5]),
				(vec.getX() * this.elements[6]) + (vec.getY() * this.elements[7]) + (vec.getZ() * this.elements[8])
		);
	}

	public Matrix3d scale(double s) {
		return new Matrix3d(
				Arrays.stream(this.elements).map((val -> val * s)).toArray()
		);
	}

	public Matrix3d(double... elements) {
		assert elements.length == 9;
		this.elements = elements;
	}

	public double get(int x, int y) {
		return elements[y * SIZE + x];
	}

	private double[] getValues() {
		return elements;
	}

	public float[] getValuesF() {
		return new float[]{
				(float) elements[0],
				(float) elements[1],
				(float) elements[2],
				(float) elements[3],
				(float) elements[4],
				(float) elements[5],
				(float) elements[6],
				(float) elements[7],
				(float) elements[8],
				(float) elements[9]
		};
	}

	public double trace() {
		return elements[0] + elements[5] + elements[10];
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Matrix3d matrix4d = (Matrix3d) o;
		return Arrays.equals(elements, matrix4d.elements);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(elements);
	}

	public Matrix3d transpose() {

		double[] newElements = new double[9];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				newElements[j * SIZE + i] = this.getValues()[i * SIZE + j];
			}
		}

		return new Matrix3d(newElements);
	}

	public Vec3d getXVec() {
		return new Vec3d(get(0,0), get(1,0), get(2,0));
	}

	public Vec3d getYVec() {
		return new Vec3d(get(0,1), get(1,1), get(2,1));
	}

	public Vec3d getZVec() {
		return new Vec3d(get(0,2), get(1,2), get(2,2));
	}

}
