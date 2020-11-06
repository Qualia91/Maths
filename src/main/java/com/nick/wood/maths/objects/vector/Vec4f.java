package com.nick.wood.maths.objects.vector;

import com.nick.wood.maths.objects.matrix.Matrix4f;

import java.util.Objects;

public class Vec4f {

	public static final Vec4f ZERO = new Vec4f(0, 0, 0, 0);
	public static final Vec4f X = new Vec4f(1, 0, 0, 0);
	public static final Vec4f Y = new Vec4f(0, 1, 0, 0);
	public static final Vec4f Z = new Vec4f(0, 0, 1, 0);
	public static final Vec4f S = new Vec4f(0, 0, 0, 1);
	public static final Vec4f ONE = new Vec4f(1, 1, 1, 1);

	private final float x;
	private final float y;
	private final float z;
	private final float s;

	public Vec4f(float x, float y, float z, float s) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.s = s;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public float getS() {
		return s;
	}

	public Vec4f add(Vec4f vec) {
		return new Vec4f(
				this.x + vec.x,
				this.y + vec.y,
				this.z + vec.z,
				this.s + vec.s
				);
	}

	public Vec4f subtract(Vec4f vec) {
		return new Vec4f(
				this.x - vec.x,
				this.y - vec.y,
				this.z - vec.z,
				this.s - vec.s
		);
	}

	public Vec4f scale(float s) {
		return new Vec4f(
				this.x * s,
				this.y * s,
				this.z * s,
				this.s * s
				);
	}

	public float[] getValues() {
		return new float[] {x, y, z, s};
	}

	public Matrix4f outerProduct(Vecf vec3f) {

		float[] elements = new float[16];

		for (int thisVecIndex = 0; thisVecIndex < this.getValues().length; thisVecIndex++) {
			for (int otherVecIndex = 0; otherVecIndex < vec3f.getValues().length; otherVecIndex++) {

				elements[thisVecIndex * 4 + otherVecIndex] = this.getValues()[thisVecIndex] * vec3f.getValues()[otherVecIndex];

			}
		}

		return new Matrix4f(elements);
	}

	public Vec4f neg() {
		return new Vec4f(
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
		Vec4f vec4f = (Vec4f) o;
		return Float.compare(vec4f.x, x) == 0 &&
				Float.compare(vec4f.y, y) == 0 &&
				Float.compare(vec4f.z, z) == 0 &&
				Float.compare(vec4f.s, s) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z, s);
	}

	@Override
	public String toString() {
		return x + ", " + y + ", " + z + ", " + s;
	}

	public float get(int i) {
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
