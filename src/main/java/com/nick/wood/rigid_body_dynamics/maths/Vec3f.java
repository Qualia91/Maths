package com.nick.wood.rigid_body_dynamics.maths;

import java.util.Objects;

public class Vec3f {

	public static final Vec3f ZERO = new Vec3f(0.0f, 0.0f, 0.0f);
	public static final Vec3f X =    new Vec3f(1.0f, 0.0f, 0.0f);
	public static final Vec3f Y =    new Vec3f(0.0f, 1.0f, 0.0f);
	public static final Vec3f Z =    new Vec3f(0.0f, 0.0f, 1.0f);
	public static final Vec3f ONE =  new Vec3f(1.0f, 1.0f, 1.0f);

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

	public Vec3f add(Vec3f vec) {
		return new Vec3f(
				this.x + vec.x,
				this.y + vec.y,
				this.z + vec.z);
	}

	public Vec3f subtract(Vec3f vec) {
		return new Vec3f(
				this.x - vec.x,
				this.y - vec.y,
				this.z - vec.z);
	}

	public Vec3f scale(float s) {
		return new Vec3f(
				this.x * s,
				this.y * s,
				this.z * s);
	}

	public float dot(Vec3f vec) {
		return
				this.x * vec.getX() +
				this.y * vec.getY() +
				this.z * vec.getZ();
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
		if (this.length() == 0.0 ) {
			return Vec3f.ZERO;
		}
		return this.scale(1/this.length());
	}

	public float[] getValues() {
		return new float[] {x, y, z};
	}

	public Matrix4f outerProduct(Vec3f vec3f) {

		float[] elements = new float[16];

		for (int thisVecIndex = 0; thisVecIndex < this.getValues().length; thisVecIndex++) {
			for (int otherVecIndex = 0; otherVecIndex < vec3f.getValues().length; otherVecIndex++) {

				elements[thisVecIndex * 4 + otherVecIndex] = this.getValues()[thisVecIndex] * vec3f.getValues()[otherVecIndex];

			}
		}

		return new Matrix4f(elements);
	}

	public Vec3f cross(Vec3f vec3d) {
		return new Vec3f(
				this.y * vec3d.z - this.z * vec3d.y,
				this.z * vec3d.x - this.x * vec3d.z,
				this.x * vec3d.y - this.y * vec3d.x
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
				0.0f, -z, y, 0.0f,
				z, 0.0f, -x, 0.0f,
				-y, x, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f
		);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vec3f vec3d = (Vec3f) o;
		return Float.compare(vec3d.x, x) == 0 &&
				Float.compare(vec3d.y, y) == 0 &&
				Float.compare(vec3d.z, z) == 0;
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
	public Vec3f multiply(Vec3f n) {
		return new Vec3f(
				x * n.getX(),
				y * n.getY(),
				z * n.getZ()
		);
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
}
