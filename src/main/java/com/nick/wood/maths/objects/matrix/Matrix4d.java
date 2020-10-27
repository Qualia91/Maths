package com.nick.wood.maths.objects.matrix;

import com.nick.wood.maths.objects.QuaternionD;
import com.nick.wood.maths.objects.QuaternionF;
import com.nick.wood.maths.objects.vector.Vec3d;

import java.util.Arrays;

public class Matrix4d {

	public static final int SIZE = 4;
	private final double[] elements;

	public static Matrix4d Identity = new Matrix4d(
			1.0, 0.0, 0.0, 0.0,
			0.0, 1.0, 0.0, 0.0,
			0.0, 0.0, 1.0, 0.0,
			0.0, 0.0, 0.0, 1.0
	);

	public static Matrix4d Translation(Vec3d vec3d) {
		return new Matrix4d(
				1.0, 0.0, 0.0, vec3d.getX(),
				0.0, 1.0, 0.0, vec3d.getY(),
				0.0, 0.0, 1.0, vec3d.getZ(),
				0.0, 0.0, 0.0, 1.0);
	}

	public static Matrix4d Rotation(double angle, Vec3d axis) {
		double[] newElems = new double[16];
		newElems[15] = 1;

		double cos = Math.cos(Math.toRadians(angle));
		double sin = Math.sin(Math.toRadians(angle));
		double C = 1 - cos;

		newElems[0 * SIZE + 0] = cos + axis.getX() * axis.getX() * C;
		newElems[1 * SIZE + 0] = axis.getX() * axis.getY() * C - axis.getZ() * sin;
		newElems[2 * SIZE + 0] = axis.getX() * axis.getZ() * C + axis.getY() * sin;
		newElems[0 * SIZE + 1] = axis.getY() * axis.getX() * C + axis.getZ() * sin;
		newElems[1 * SIZE + 1] = cos + axis.getY() * axis.getY() * C;
		newElems[2 * SIZE + 1] = axis.getY() * axis.getZ() * C - axis.getX() * sin;
		newElems[0 * SIZE + 2] = axis.getZ() * axis.getX() * C - axis.getY() * sin;
		newElems[1 * SIZE + 2] = axis.getZ() * axis.getY() * C + axis.getX() * sin;
		newElems[2 * SIZE + 2] = cos + axis.getZ() * axis.getZ() * C;

		return new Matrix4d(newElems);
	}

	private static Matrix4d Scale(Vec3d scale) {
		return new Matrix4d(
				scale.getX(), 0.0, 0.0, 0.0,
				0.0, scale.getY(), 0.0, 0.0,
				0.0, 0.0, scale.getZ(), 0.0,
				0.0, 0.0, 0.0, 1.0
		);
	}

	public Matrix4d add(Matrix4d matrix) {

		double[] newElements = new double[16];

		for (int i = 0; i < this.elements.length; i++) {
			newElements[i] = this.elements[i] + matrix.getValues()[i];
		}

		return new Matrix4d(newElements);
	}

	public Matrix4d add(Vec3d vec3d) {
		return new Matrix4d(
				elements[0] + vec3d.getX(), elements[1], elements[2], elements[3],
				elements[4], elements[5] + vec3d.getY(), elements[6], elements[7],
				elements[8], elements[9], elements[10] + vec3d.getZ(), elements[11],
				elements[12], elements[13], elements[14], elements[15]
		);
	}

	public Matrix4d multiply(Matrix4d matrix4d) {

		double[] newElements = new double[16];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				newElements[j * SIZE + i] =
						this.elements[i + SIZE * 0] * matrix4d.getValues()[0 + j * SIZE] +
								this.elements[i + SIZE * 1] * matrix4d.getValues()[1 + j * SIZE] +
								this.elements[i + SIZE * 2] * matrix4d.getValues()[2 + j * SIZE] +
								this.elements[i + SIZE * 3] * matrix4d.getValues()[3 + j * SIZE];
			}
		}

		return new Matrix4d(newElements);

	}

	public Vec3d multiply(Vec3d vec) {
		return new Vec3d(
				(vec.getX() * this.elements[0]) + (vec.getY() * this.elements[1]) + (vec.getZ() * this.elements[2]) + this.elements[3],
				(vec.getX() * this.elements[4]) + (vec.getY() * this.elements[5]) + (vec.getZ() * this.elements[6]) + this.elements[7],
				(vec.getX() * this.elements[8]) + (vec.getY() * this.elements[9]) + (vec.getZ() * this.elements[10] + this.elements[11])
		);
	}

	public Vec3d rotate(Vec3d vec) {
		return new Vec3d(
				(vec.getX() * this.elements[0]) + (vec.getY() * this.elements[1]) + (vec.getZ() * this.elements[2]),
				(vec.getX() * this.elements[4]) + (vec.getY() * this.elements[5]) + (vec.getZ() * this.elements[6]),
				(vec.getX() * this.elements[8]) + (vec.getY() * this.elements[9]) + (vec.getZ() * this.elements[10])
		);
	}

	public Matrix4d scale(double s) {
		return new Matrix4d(
				Arrays.stream(this.elements).map((val -> val * s)).toArray()
		);
	}

	public Matrix4d(double... elements) {
		assert elements.length == 16;
		this.elements = elements;
	}

	public double get(int x, int y) {
		return elements[y * SIZE + x];
	}

	public double[] getValues() {
		return elements;
	}

	public Matrix3d getRotation() {
		return new Matrix3d(
				elements[0], elements[1], elements[2],
				elements[4], elements[5], elements[6],
				elements[8], elements[9], elements[10]
		);
	}

	public Vec3d getTranslation() {
		return new Vec3d(
				elements[3], elements[7], elements[11]
		);
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

	public static Matrix4d Transform(Vec3d pos, Matrix4d rot, Vec3d scale) {

		Matrix4d translation = Translation(pos);
		Matrix4d scaleMatrix = Scale(scale);

		return scaleMatrix.multiply(rot).multiply(translation);

	}

	public static Matrix4d InverseTransformation(Vec3d pos, Matrix4d rot, Vec3d scale) {
		Matrix4d translation = Translation(pos.neg());
		Matrix4d scaleMatrix = Scale(scale);

		return translation.multiply(rot.transpose()).multiply(scaleMatrix);
	}

	public static Matrix4d Projection(double aspect, double fov, double near, double far) {

		double tanHalfFov = Math.tan(fov / 2.0);
		double farNearDel = far - near;

		return new Matrix4d(
				1.0 / (aspect * tanHalfFov), 0.0, 0.0, 0.0,
				0.0, 1.0 / tanHalfFov, 0.0, 0.0,
				0.0, 0.0, -(far + near) / farNearDel, -(2 * far * near) / farNearDel,
				0.0, 0.0, -1.0, 0.0
		);

	}

	public Matrix4d updateProjection(double aspect, double fov) {

		double tanHalfFov = Math.tan(fov / 2.0);

		return new Matrix4d(
				1.0 / (aspect * tanHalfFov), 0, 0, 0,
				0, this.get(1, 1), 0, 0,
				0, 0, this.get(2, 2), this.get(3, 2),
				0, 0, -1, 0
		);

	}

	public static Matrix4d View(Vec3d pos, Vec3d rot) {

		Matrix4d translation = Translation(pos.neg());
		Matrix4d rotationX = Rotation(rot.getX(), Vec3d.X);
		Matrix4d rotationY = Rotation(rot.getY(), Vec3d.Y);
		Matrix4d rotationZ = Rotation(rot.getZ(), Vec3d.Z);
		Matrix4d rotation = rotationZ.multiply(rotationY).multiply(rotationX);

		return translation.multiply(rotation);
	}

	public double trace() {
		return elements[0] + elements[5] + elements[10];
	}

	public QuaternionD toQuaternion() {

		double q0;
		double q1;
		double q2;
		double q3;

		// check that trace is bigger than 0
		double trace = trace();
		if (trace > 0) {
			// if it is, the matrix is a pure rotation and so you can use the simple conversion
			// this version is used to reduce numerical errors when dividing by really small numbers
			double traceAddition = Math.sqrt(trace + 1) * 2.0;
			q0 = traceAddition * 0.25;
			double q04 = 4 * q0;
			q1 = get(2, 1) - get(1, 2) / q04;
			q2 = get(0, 2) - get(2, 0) / q04;
			q3 = get(1, 0) - get(0, 1) / q04;
		}
		// if the trace is less than or equal to 0, identify which major diagonal element has the greatest value
		else if ((get(0, 0) > get(1, 1) & get(0, 0) > get(2, 2))) {
			double S = Math.sqrt(1.0 + get(0, 0) - get(1, 1) - get(2, 2)) * 2; // S=4*qx
			q0 = (get(2, 1) - get(1, 2)) / S;
			q1 = 0.25 * S;
			q2 = (get(0, 1) + get(1, 0)) / S;
			q3 = (get(0, 2) + get(2, 0)) / S;
		} else if (get(1, 1) > get(2, 2)) {
			double S = Math.sqrt(1.0 + get(1, 1) - get(0, 0) - get(2, 2)) * 2; // S=4*qy
			q0 = (get(0, 2) - get(2, 0)) / S;
			q1 = (get(0, 1) + get(1, 0)) / S;
			q2 = 0.25 * S;
			q3 = (get(1, 2) + get(2, 1)) / S;
		} else {
			double S = Math.sqrt(1.0 + get(2, 2) - get(0, 0) - get(1, 1)) * 2; // S=4*qz
			q0 = (get(1, 0) - get(0, 1)) / S;
			q1 = (get(0, 2) + get(2, 0)) / S;
			q2 = (get(1, 2) + get(2, 1)) / S;
			q3 = 0.25 * S;
		}

		return new QuaternionD(q0, q1, q2, q3);


	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Matrix4d matrix4d = (Matrix4d) o;
		return Arrays.equals(elements, matrix4d.elements);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(elements);
	}

	public Matrix4d transpose() {

		double[] newElements = new double[16];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				newElements[j * SIZE + i] = this.getValues()[i * SIZE + j];
			}
		}

		return new Matrix4d(newElements);
	}

	public Vec3d getXVec() {
		return new Vec3d(get(0, 0), get(1, 0), get(2, 0));
	}

	public Vec3d getYVec() {
		return new Vec3d(get(0, 1), get(1, 1), get(2, 1));
	}

	public Vec3d getZVec() {
		return new Vec3d(get(0, 2), get(1, 2), get(2, 2));
	}

	public Matrix4f toMatrix4f() {

		float[] newElems = new float[elements.length];

		for (int i = 0; i < elements.length; i++) {
			newElems[i] = (float) elements[i];
		}

		return new Matrix4f(newElems);
	}

	// todo implement gauss jordon elimination

	//public double det() {

	//}

	// adjugate matrix method
	//public Matrix4d inverse() {


	//}
}
