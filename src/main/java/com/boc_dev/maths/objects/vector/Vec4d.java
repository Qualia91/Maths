package com.boc_dev.maths.objects.vector;

import com.boc_dev.maths.objects.matrix.Matrix4d;

import java.util.Objects;

public class Vec4d {

	public static final Vec4d ZERO = new Vec4d(0, 0, 0, 0);
	public static final Vec4d X = new Vec4d(1, 0, 0, 0);
	public static final Vec4d Y = new Vec4d(0, 1, 0, 0);
	public static final Vec4d Z = new Vec4d(0, 0, 1, 0);
	public static final Vec4d S = new Vec4d(0, 0, 0, 1);
	public static final Vec4d ONE = new Vec4d(1, 1, 1, 1);

	private final double x;
	private final double y;
	private final double z;
	private final double s;

	public Vec4d(double x, double y, double z, double s) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.s = s;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public double getS() {
		return s;
	}

	public Vec4d add(Vec4d vec) {
		return new Vec4d(
				this.x + vec.x,
				this.y + vec.y,
				this.z + vec.z,
				this.s + vec.s
				);
	}

	public Vec4d subtract(Vec4d vec) {
		return new Vec4d(
				this.x - vec.x,
				this.y - vec.y,
				this.z - vec.z,
				this.s - vec.s
		);
	}

	public Vec4d scale(double s) {
		return new Vec4d(
				this.x * s,
				this.y * s,
				this.z * s,
				this.s * s
				);
	}

	public double[] getValues() {
		return new double[] {x, y, z};
	}

	public Matrix4d outerProduct(Vecf vec3f) {

		double[] elements = new double[16];

		for (int thisVecIndex = 0; thisVecIndex < this.getValues().length; thisVecIndex++) {
			for (int otherVecIndex = 0; otherVecIndex < vec3f.getValues().length; otherVecIndex++) {

				elements[thisVecIndex * 4 + otherVecIndex] = this.getValues()[thisVecIndex] * vec3f.getValues()[otherVecIndex];

			}
		}

		return new Matrix4d(elements);
	}

	public Vec4d neg() {
		return new Vec4d(
				-this.x,
				-this.y,
				-this.z,
				-this.s
		);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vec4d vec4f = (Vec4d) o;
		return Double.compare(vec4f.x, x) == 0 &&
				Double.compare(vec4f.y, y) == 0 &&
				Double.compare(vec4f.z, z) == 0 &&
				Double.compare(vec4f.s, s) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z, s);
	}

	@Override
	public String toString() {
		return x + ", " + y + ", " + z + ", " + s;
	}

	public double get(int i) {
		switch (i) {
			case 0:
				return x;
			case 1:
				return y;
			case 2:
				return z;
			case 3:
				return s;
			default:
				throw new RuntimeException(i + " is out of bounds for current vector");
		}
	}

	public double[] getValuesD() {
		return new double[] {x, y, z};
	}

}
