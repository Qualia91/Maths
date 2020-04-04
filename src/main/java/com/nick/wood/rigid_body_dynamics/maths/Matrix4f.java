package com.nick.wood.rigid_body_dynamics.maths;

import java.util.Arrays;

public class Matrix4f {

	public static final int SIZE = 4;
	private final float[] elements;

	public static Matrix4f Identity = new Matrix4f(
			1.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 1.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 1.0f
	);

	public static Matrix4f Translation(Vec3f vec3f) {
		return new Matrix4f(
				1.0f, 0.0f, 0.0f, vec3f.getX(),
				0.0f, 1.0f, 0.0f, vec3f.getY(),
				0.0f, 0.0f, 1.0f, vec3f.getZ(),
				0.0f, 0.0f, 0.0f, 1.0f);
	}

	public static Matrix4f Rotation(float angle, Vec3f axis) {
		float[] newElems = new float[16];
		newElems[15] = 1;

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

		return new Matrix4f(newElems);
	}

	private static Matrix4f Scale(Vec3f scale) {
		return new Matrix4f(
				scale.getX(), 0.0f, 0.0f, 0.0f,
				0.0f, scale.getY(), 0.0f, 0.0f,
				0.0f, 0.0f, scale.getZ(), 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f
		);
	}

	public Matrix4f add(Matrix4f matrix) {

		float[] newElements = new float[16];

		for (int i = 0; i < this.elements.length; i++) {
			newElements[i] = this.elements[i] + matrix.getValues()[i];
		}

		return new Matrix4f(newElements);
	}

	public Matrix4f add(Vec3f vec3f) {
		return new Matrix4f(
				elements[0] + vec3f.getX(), elements[1], elements[2], elements[3],
				elements[4], elements[5] + vec3f.getY(), elements[6], elements[7],
				elements[8], elements[9], elements[10] + vec3f.getZ(), elements[11],
				elements[12], elements[13], elements[14], elements[15]
		);
	}

	public Matrix4f multiply(Matrix4f matrix4d) {

		float[] newElements = new float[16];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				newElements[j * SIZE + i] =
						this.elements[i + SIZE * 0] * matrix4d.getValues()[0 + j * SIZE] +
								this.elements[i + SIZE * 1] * matrix4d.getValues()[1 + j * SIZE] +
								this.elements[i + SIZE * 2] * matrix4d.getValues()[2 + j * SIZE] +
								this.elements[i + SIZE * 3] * matrix4d.getValues()[3 + j * SIZE];
			}
		}

		return new Matrix4f(newElements);

	}

	public Vec3f multiply(Vec3f vec) {
		return new Vec3f(
				(vec.getX() * this.elements[0]) + (vec.getY() * this.elements[1]) + (vec.getZ() * this.elements[2]) + this.elements[3],
				(vec.getX() * this.elements[4]) + (vec.getY() * this.elements[5]) + (vec.getZ() * this.elements[6]) + this.elements[7],
				(vec.getX() * this.elements[8]) + (vec.getY() * this.elements[9]) + (vec.getZ() * this.elements[10] + this.elements[11])
		);
	}

	public Matrix4f(float... elements) {
		assert elements.length == 16;
		this.elements = elements;
	}

	public float get(int x, int y) {
		return elements[y * SIZE + x];
	}

	private float[] getValues() {
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
				(float) elements[9],
				(float) elements[10],
				(float) elements[11],
				(float) elements[12],
				(float) elements[13],
				(float) elements[14],
				(float) elements[15]
		};
	}

	public static Matrix4f Transform(Vec3f pos, Matrix4f rot, Vec3f scale) {

		Matrix4f translation = Translation(pos);
		Matrix4f scaleMatrix = Scale(scale);

		return scaleMatrix.multiply(rot).multiply(translation);

	}

	public static Matrix4f InverseTransformation(Vec3f pos, Matrix4f rot, Vec3f scale) {
		Matrix4f translation = Translation(pos);
		Matrix4f scaleMatrix = Scale(scale);

		return translation.multiply(rot).multiply(scaleMatrix);
	}

	public static Matrix4f Projection(float aspect, float fov, float near, float far) {

		float tanHalfFov = (float) Math.tan(fov / 2.0);
		float farNearDel = far - near;

		return new Matrix4f(
				1.0f / (aspect * tanHalfFov), 0.0f, 0.0f, 0.0f,
				0.0f, 1.0f / tanHalfFov, 0.0f, 0.0f,
				0.0f, 0.0f, - (far + near)/farNearDel, - (2 * far * near) / farNearDel,
				0.0f, 0.0f, -1.0f, 0.0f
		);

	}

	public static Matrix4f View(Vec3f pos, Vec3f rot) {

		Matrix4f translation = Translation(pos.neg());
		Matrix4f rotationX = Rotation(rot.getX(), Vec3f.X);
		Matrix4f rotationY = Rotation(rot.getY(), Vec3f.Y);
		Matrix4f rotationZ = Rotation(rot.getZ(), Vec3f.Z);
		Matrix4f rotation = rotationZ.multiply(rotationY).multiply(rotationX);

		return translation.multiply(rotation);
	}

	public float trace() {
		return elements[0] + elements[5] + elements[10];
	}

	public Quaternion toQuaternion() {
		double trace = trace();
		double traceAddition = (1 - trace) / 4.0;
		double q0 = Math.sqrt((trace + 1.0) / 4.0);
		double q1 = Math.sqrt((elements[0]/2) + traceAddition);
		double q2 = Math.sqrt((elements[5]/2) + traceAddition);
		double q3 = Math.sqrt((elements[10]/2) + traceAddition);

		return new Quaternion(q0, q1, q2, q3);

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Matrix4f matrix4d = (Matrix4f) o;
		return Arrays.equals(elements, matrix4d.elements);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(elements);
	}

	public Matrix4f transpose() {

		float[] newElements = new float[16];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				newElements[j * SIZE + i] = this.getValues()[i * SIZE + j];
			}
		}

		return new Matrix4f(newElements);
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
