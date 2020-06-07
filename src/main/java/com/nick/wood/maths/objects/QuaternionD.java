package com.nick.wood.maths.objects;

import com.nick.wood.maths.objects.matrix.Matrix4d;
import com.nick.wood.maths.objects.vector.Vec3d;

import java.util.Arrays;

public class QuaternionD {

	private final double[] q;

	public static QuaternionD FromVec(double s, Vec3d vec) {
		return new QuaternionD(s, vec.getX(), vec.getY(), vec.getZ());
	}

	public static QuaternionD Rotation(Vec3d rotationVec) {
		return new QuaternionD(rotationVec);
	}

	/**
	 * In radians
	 *
	 * @param angle in radians
	 * @return
	 */
	public static QuaternionD RotationX(double angle) {
		return new QuaternionD(Math.cos(angle / 2), Math.sin(angle / 2), 0.0, 0.0);
	}

	public static QuaternionD RotationY(double angle) {
		return new QuaternionD(Math.cos(angle / 2), 0.0, Math.sin(angle / 2), 0.0);
	}

	public static QuaternionD RotationZ(double angle) {
		return new QuaternionD(Math.cos(angle / 2), 0.0, 0.0, Math.sin(angle / 2));
	}

	public QuaternionD normalise() {
		return this.scale(1 / this.len());
	}

	public QuaternionD(double s, double i, double j, double k) {
		q = new double[4];
		q[0] = s;
		q[1] = i;
		q[2] = j;
		q[3] = k;
	}

	public double getS() {
		return q[0];
	}

	public double getI() {
		return q[1];
	}

	public double getJ() {
		return q[2];
	}

	public double getK() {
		return q[3];
	}

	private QuaternionD(double... q) {
		this.q = q;
	}

	public QuaternionD(Vec3d vec) {
		this.q = new double[]{0.0, vec.getX(), vec.getY(), vec.getZ()};
	}

	public QuaternionD rotateVector(QuaternionD vector) {
		return this.multiply(vector.multiply(this.conjugate()));
	}

	public QuaternionD add(QuaternionD p) {
		return new QuaternionD(
				q[0] + p.getQ()[0],
				q[1] + p.getQ()[1],
				q[2] + p.getQ()[2],
				q[3] + p.getQ()[3]);
	}

	public QuaternionD multiply(QuaternionD p) {
		return new QuaternionD(
				(q[0] * p.getQ()[0]) - (q[1] * p.getQ()[1]) - (q[2] * p.getQ()[2]) - (q[3] * p.getQ()[3]),
				(q[1] * p.getQ()[0]) + (q[0] * p.getQ()[1]) + (q[2] * p.getQ()[3]) - (q[3] * p.getQ()[2]),
				(q[0] * p.getQ()[2]) - (q[1] * p.getQ()[3]) + (q[2] * p.getQ()[0]) + (q[3] * p.getQ()[1]),
				(q[0] * p.getQ()[3]) + (q[1] * p.getQ()[2]) - (q[2] * p.getQ()[1]) + (q[3] * p.getQ()[0])
		);
	}

	public Matrix4d toMatrix() {

		double[] qNorm = (this.normalise()).getQ();

		double q00 = qNorm[0] * qNorm[0];
		double q01 = qNorm[0] * qNorm[1];
		double q02 = qNorm[0] * qNorm[2];
		double q03 = qNorm[0] * qNorm[3];

		double q11 = qNorm[1] * qNorm[1];
		double q12 = qNorm[1] * qNorm[2];
		double q13 = qNorm[1] * qNorm[3];

		double q22 = qNorm[2] * qNorm[2];
		double q23 = qNorm[2] * qNorm[3];

		double q33 = qNorm[3] * qNorm[3];

		return new Matrix4d(
				q00 + q11 - 0.5, q12 - q03, q02 + q13, 0.0,
				q03 + q12, q00 + q22 - 0.5, q23 - q01, 0.0,
				q13 - q02, q01 + q23, q00 + q33 - 0.5, 0.0,
				0.0, 0.0, 0.0, 0.5
		).scale(2);

	}

	public QuaternionD scale(double s) {
		return new QuaternionD(q[0] * s, q[1] * s, q[2] * s, q[3] * s);
	}

	public QuaternionD conjugate() {
		return new QuaternionD(q[0], -q[1], -q[2], -q[3]);
	}

	public double len2() {
		return (q[0] * q[0]) + (q[1] * q[1]) + (q[2] * q[2]) + (q[3] * q[3]);
	}

	public double len() {
		return Math.sqrt(len2());
	}

	public QuaternionD inverse() {
		return conjugate().scale(1 / len());
	}

	private double[] getQ() {
		return q;
	}

	public Vec3d toVec3d() {
		return new Vec3d(q[1], q[2], q[3]);
	}

	/** returns yaw pitch roll
	 *
	 * @return
	 */
	public Vec3d toEulerAngles() {

		// roll (x-axis rotation)
		double sinr_cosp = 2 * (getS() * getI() + getJ() * getK());
		double cosr_cosp = 1 - 2 * (getI() * getI() + getJ() * getJ());
		double roll = Math.atan2(sinr_cosp, cosr_cosp);

		// pitch (y-axis rotation)
		double pitch;
		double sinp = 2 * (getS() * getJ() - getK() * getI());
		if (Math.abs(sinp) >= 1){
			pitch = Math.copySign (Math.PI / 2, sinp); // use 90 degrees if out of range
		}
        else{
			pitch = Math.asin (sinp);
		}

		// yaw (z-axis rotation)
		double siny_cosp = 2 * (getS() * getK() + getI() * getJ());
		double cosy_cosp = 1 - 2 * (getJ() * getJ() + getK() * getK());
		double yaw = Math.atan2(siny_cosp, cosy_cosp);

		return new Vec3d(yaw, pitch, roll);
	}

	@Override
	public String toString() {
		return Arrays.toString(q);
	}
}
