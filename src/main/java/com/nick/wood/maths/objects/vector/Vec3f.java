package com.nick.wood.maths.objects.vector;

import com.nick.wood.maths.objects.matrix.Matrix4f;

import java.util.Objects;

public class Vec3f implements Vecf {

	public static final Vec3f ZERO = new Vec3f(0, 0, 0);
	public static final Vec3f X = new Vec3f(1, 0, 0);
	public static final Vec3f Y = new Vec3f(0, 1, 0);
	public static final Vec3f Z = new Vec3f(0, 0, 1);
	public static final Vec3f ONE = new Vec3f(1, 1, 1);

	private final float x;
	private final float y;
	private final float z;

	public Vec3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// Create a restricted vector
	public Vec3f(Vec3f momentumUnrestricted, float limit) {
		x = Math.min(momentumUnrestricted.getX(), limit);
		y = Math.min(momentumUnrestricted.getY(), limit);
		z = Math.min(momentumUnrestricted.getZ(), limit);
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

	public Vec3f add(Vecf vec) {
		assert vec instanceof Vec3f;
		Vec3f vec3f = (Vec3f) vec;
		return new Vec3f(
				this.x + vec3f.x,
				this.y + vec3f.y,
				this.z + vec3f.z);
	}

	public Vec3f subtract(Vecf vec) {
		assert vec instanceof Vec3f;
		Vec3f vec3f = (Vec3f) vec;
		return new Vec3f(
				this.x - vec3f.x,
				this.y - vec3f.y,
				this.z - vec3f.z);
	}

	public Vec3f scale(float s) {
		return new Vec3f(
				this.x * s,
				this.y * s,
				this.z * s);
	}

	public float dot(Vecf vec) {
		assert vec instanceof Vec3f;
		Vec3f vec3f = (Vec3f) vec;
		return
				this.x * vec3f.getX() +
				this.y * vec3f.getY() +
				this.z * vec3f.getZ();
	}

	public float length2() {
		return
				(this.x * this.x) +
				(this.y * this.y) +
				(this.z * this.z);
	}

	public float length() {
		return (float) Math.sqrt(length2());
	}

	public Vec3f normalise() {
		if (this.length() == 0.0f ) {
			return Vec3f.ZERO;
		}
		return this.scale(1f/this.length());
	}

	public float[] getValues() {
		return new float[] {x, y, z};
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

	public Vec3f cross(Vecf vec) {
		assert vec instanceof Vec3f;
		Vec3f vec3f = (Vec3f) vec;
		return new Vec3f(
				this.y * vec3f.z - this.z * vec3f.y,
				this.z * vec3f.x - this.x * vec3f.z,
				this.x * vec3f.y - this.y * vec3f.x
		);
	}

	public Vec3f neg() {
		return new Vec3f(
				-this.x,
				-this.y,
				-this.z
		);
	}

	// used for integration
	public Matrix4f star() {
		return new Matrix4f(
				0, -z, y, 0,
				z, 0, -x, 0,
				-y, x, 0, 0,
				0, 0, 0, 1
		);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vec3f vec3f = (Vec3f) o;
		return Double.compare(vec3f.x, x) == 0 &&
				Double.compare(vec3f.y, y) == 0 &&
				Double.compare(vec3f.z, z) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public String toString() {
		return x + ", " + y + ", " + z;
	}

	public Vec3f multiply(Matrix4f m) {
		return new Vec3f(
				this.dot(m.getXVec()),
				this.dot(m.getYVec()),
				this.dot(m.getZVec())
		);
	}

	// element wise multiplication
	public Vec3f multiply(Vecf vec) {
		assert vec instanceof Vec3f;
		Vec3f vec3f = (Vec3f) vec;
		return new Vec3f(
				x * vec3f.getX(),
				y * vec3f.getY(),
				z * vec3f.getZ()
		);
	}

	@Override
	public float get(int i) {
		switch (i) {
			case 0:
				return x;
			case 1:
				return y;
			case 2:
				return z;
			default:
				throw new RuntimeException(i + " is out of bounds for current vector");
		}
	}

    @Override
    public Vec3d toVecd() {
        return new Vec3d(x, y, z);
    }

    public static Vec3f Min(Vec3f a, Vec3f b) {
		return new Vec3f(
				Math.min(a.getX(), b.getX()),
				Math.min(a.getY(), b.getY()),
				Math.min(a.getZ(), b.getZ())
		);
	}

	public static Vec3f Max(Vec3f a, Vec3f b) {
		return new Vec3f(
				Math.max(a.getX(), b.getX()),
				Math.max(a.getY(), b.getY()),
				Math.max(a.getZ(), b.getZ())
		);
	}

	public double[] getValuesD() {
		return new double[] {x, y, z};
	}

}
