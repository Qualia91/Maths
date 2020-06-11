package com.nick.wood.maths.objects;

import com.nick.wood.maths.objects.matrix.Matrix4f;
import com.nick.wood.maths.objects.vector.Vec3f;

import java.util.Arrays;

public class QuaternionF {

	public static final QuaternionF Identity = new QuaternionF(1, 0, 0, 0);

	private final float[] q;

	public static QuaternionF FromVec(float s, Vec3f vec) {
		return new QuaternionF(s, vec.getX(), vec.getY(), vec.getZ());
	}

	public static QuaternionF Rotation(Vec3f rotationVec) {
		return new QuaternionF(rotationVec);
	}

	/**
	 * In radians
	 *
	 * @param angle in radians
	 * @return
	 */
	public static QuaternionF RotationX(float angle) {
		return new QuaternionF(Math.cos(angle / 2), Math.sin(angle / 2), 0.0, 0.0);
	}

	public static QuaternionF RotationY(float angle) {
		return new QuaternionF(Math.cos(angle / 2), 0.0, Math.sin(angle / 2), 0.0);
	}

	public static QuaternionF RotationZ(float angle) {
		return new QuaternionF(Math.cos(angle / 2), 0.0, 0.0, Math.sin(angle / 2));
	}

	public QuaternionF normalise() {
		return this.scale(1 / this.len());
	}

	public QuaternionF(float s, float i, float j, float k) {
		q = new float[4];
		q[0] = s;
		q[1] = i;
		q[2] = j;
		q[3] = k;
	}

	public QuaternionF(double s, double i, double j, double k) {
		q = new float[4];
		q[0] = (float) s;
		q[1] = (float) i;
		q[2] = (float) j;
		q[3] = (float) k;
	}

	public float getS() {
		return q[0];
	}

	public float getI() {
		return q[1];
	}

	public float getJ() {
		return q[2];
	}

	public float getK() {
		return q[3];
	}

	private QuaternionF(float... q) {
		this.q = q;
	}

	public QuaternionF(Vec3f vec) {
		this.q = new float[]{0, vec.getX(), vec.getY(), vec.getZ()};
	}

	public QuaternionF rotateVector(Vec3f vector) {
		return this.multiply(FromVec(0, vector)).multiply(this.conjugate());
	}

	public QuaternionF add(QuaternionF p) {
		return new QuaternionF(
				q[0] + p.getQ()[0],
				q[1] + p.getQ()[1],
				q[2] + p.getQ()[2],
				q[3] + p.getQ()[3]);
	}

	public QuaternionF multiply(QuaternionF p) {
		return new QuaternionF(
				(q[0] * p.getQ()[0]) - (q[1] * p.getQ()[1]) - (q[2] * p.getQ()[2]) - (q[3] * p.getQ()[3]),
				(q[1] * p.getQ()[0]) + (q[0] * p.getQ()[1]) + (q[2] * p.getQ()[3]) - (q[3] * p.getQ()[2]),
				(q[0] * p.getQ()[2]) - (q[1] * p.getQ()[3]) + (q[2] * p.getQ()[0]) + (q[3] * p.getQ()[1]),
				(q[0] * p.getQ()[3]) + (q[1] * p.getQ()[2]) - (q[2] * p.getQ()[1]) + (q[3] * p.getQ()[0])
		);
	}

	public Matrix4f toMatrix() {

		float[] qNorm = (this.normalise()).getQ();

		float q00 = qNorm[0] * qNorm[0];
		float q01 = qNorm[0] * qNorm[1];
		float q02 = qNorm[0] * qNorm[2];
		float q03 = qNorm[0] * qNorm[3];

		float q11 = qNorm[1] * qNorm[1];
		float q12 = qNorm[1] * qNorm[2];
		float q13 = qNorm[1] * qNorm[3];

		float q22 = qNorm[2] * qNorm[2];
		float q23 = qNorm[2] * qNorm[3];

		float q33 = qNorm[3] * qNorm[3];

		return new Matrix4f(
				q00 + q11 - 0.5f, q12 - q03, q02 + q13, 0,
				q03 + q12, q00 + q22 - 0.5f, q23 - q01, 0,
				q13 - q02, q01 + q23, q00 + q33 - 0.5f, 0,
				0, 0, 0, 0.5f
		).scale(2);

	}

	public QuaternionF scale(float s) {
		return new QuaternionF(q[0] * s, q[1] * s, q[2] * s, q[3] * s);
	}

	public QuaternionF conjugate() {
		return new QuaternionF(q[0], -q[1], -q[2], -q[3]);
	}

	public float len2() {
		return (q[0] * q[0]) + (q[1] * q[1]) + (q[2] * q[2]) + (q[3] * q[3]);
	}

	public float len() {
		return (float) Math.sqrt(len2());
	}

	public QuaternionF inverse() {
		return conjugate().scale(1 / len());
	}

	private float[] getQ() {
		return q;
	}

	public Vec3f toVec3f() {
		return new Vec3f(q[1], q[2], q[3]);
	}

	/** returns yaw pitch roll
	 *
	 * @return
	 */
	public Vec3f toEulerAngles() {

		// roll (x-axis rotation)
		float sinr_cosp = 2 * (getS() * getI() + getJ() * getK());
		float cosr_cosp = 1 - 2 * (getI() * getI() + getJ() * getJ());
		float roll = (float) Math.atan2(sinr_cosp, cosr_cosp);

		// pitch (y-axis rotation)
		float pitch;
		float sinp = 2 * (getS() * getJ() - getK() * getI());
		if (Math.abs(sinp) >= 1){
			pitch = (float) Math.copySign (Math.PI / 2, sinp); // use 90 degrees if out of range
		}
        else{
			pitch = (float) Math.asin (sinp);
		}

		// yaw (z-axis rotation)
		float siny_cosp = 2 * (getS() * getK() + getI() * getJ());
		float cosy_cosp = 1 - 2 * (getJ() * getJ() + getK() * getK());
		float yaw = (float) Math.atan2(siny_cosp, cosy_cosp);

		return new Vec3f(yaw, pitch, roll);
	}

	public float dot(QuaternionF q2) {
	    return getI() * q2.getI() + getJ() * q2.getJ() +
                getK() * q2.getK() + getS() * q2.getS();
    }

	public QuaternionF lerp(QuaternionF q2, float percent) {
        return (this.scale(1 - percent).add(q2)).normalise();
    }

    public QuaternionF slerp(QuaternionF q2, float percent) {
	    double theta = dot(q2);
	    float wp = (float) (Math.sin(1 - percent) * theta / Math.sin(theta));
	    float wq = (float) (Math.sin(percent) * theta / Math.sin(theta));
        return (this.scale(wp).add(q2.scale(wq))).normalise();
    }

	@Override
	public String toString() {
		return Arrays.toString(q);
	}
}
